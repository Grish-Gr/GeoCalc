package maks.ter.geocalc.service;

import maks.ter.geocalc.dto.EdPriority;
import maks.ter.geocalc.dto.EducationDto;
import maks.ter.geocalc.model.Block3DigitalEducation;
import maks.ter.geocalc.model.Block3GeneralTerms;
import maks.ter.geocalc.model.Block5;
import maks.ter.geocalc.repository.Block3DigitalEducationRepo;
import maks.ter.geocalc.repository.Block3GeneralTermsRepo;
import maks.ter.geocalc.repository.Block5Repo;
import maks.ter.geocalc.service.dto.EducationDetailsDto;
import maks.ter.geocalc.service.dto.MonthDataDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EducationDataService extends DataService {

    private Block3DigitalEducationRepo block3DigitalEducationRepo;
    private Block3GeneralTermsRepo block3GeneralTermsRepo;

    @Autowired
    public EducationDataService(DataUtils dataUtils,
                                Block5Repo block5Repo,
                                Block3DigitalEducationRepo block3DigitalEducationRepo,
                                Block3GeneralTermsRepo block3GeneralTermsRepo
    ) {
        super(dataUtils, block5Repo);
        this.block3DigitalEducationRepo = block3DigitalEducationRepo;
        this.block3GeneralTermsRepo = block3GeneralTermsRepo;
    }

    public List<EducationDetailsDto> getDataList(EducationDto educationDto) {

        List<Block3DigitalEducation> dataList;
        Pageable pageable = PageRequest.of(0, educationDto.getMaxResult());
        System.out.println(educationDto.getEducationLevel().getDescription());
        if (educationDto.getPriority() == EdPriority.AMOUNT_CONTRACT) {
            dataList = block3DigitalEducationRepo.findAllByProgramIgnoreCaseOrderByMinContractAsc(educationDto.getEducationLevel().getCode(), pageable);
        } else if (educationDto.getPriority() == EdPriority.CONTRACT_COUNT) {
            dataList = block3DigitalEducationRepo.findAllByProgramIgnoreCaseOrderByContractCountDesc(educationDto.getEducationLevel().getCode(), pageable);
        } else {
            dataList = block3DigitalEducationRepo.findAllByProgramIgnoreCaseOrderByBudgetCountDesc(educationDto.getEducationLevel().getCode(), pageable);
        }

        int rate = 1;
        List<EducationDetailsDto> result = new ArrayList<>();

        for (Block3DigitalEducation dataInfo: dataList) {

            MonthDataDto monthDataDto = getMonthData(dataInfo.getRegion());

            EducationDetailsDto educationDetailsDto = EducationDetailsDto.builder()
                    .rate(rate)
                    .region(dataInfo.getRegion())
                    .contractCount(dataInfo.getContractCount())
                    .minPriceContract(new BigDecimal(dataInfo.getMinContract()))
                    .budgetCount(dataInfo.getBudgetCount())
                    .levelEducation(getEduEnv(dataInfo.getRegion()))
                    .levelLive(getLevelLive(dataInfo.getRegion()))
                    .monthSalary(monthDataDto.getMonthSalary())
                    .monthExpense(monthDataDto.getMonthExpense())
                    .indexPrice(monthDataDto.getIndexPrice())
                    .countPeopleMinSalary(monthDataDto.getCountPeopleMinSalary())
                    .build();

            result.add(educationDetailsDto);
        }

        return result;
    }

    private Double getEduEnv(String region) {

        long currentYear = Year.now().getValue() - 1;
        List<Block3GeneralTerms> dataList = new ArrayList<>();

        while (dataList.isEmpty() && currentYear < 2018) {
            dataList = block3GeneralTermsRepo.findAllByYearResAndRegion(currentYear, region);
            currentYear--;
        }

        if (dataList.isEmpty()) {
            return null;
        }

        Double stud = dataUtils.getStandardDoubleValue(dataList.stream().map(Block3GeneralTerms::getStud).toList());
        Double studPop = dataUtils.getStandardLongValue(dataList.stream().map(Block3GeneralTerms::getStudPop).toList());
        Double admission = dataUtils.getStandardDoubleValue(dataList.stream().map(Block3GeneralTerms::getAdmission).toList());
        Double graduation = dataUtils.getStandardDoubleValue(dataList.stream().map(Block3GeneralTerms::getGraduation).toList());

        return (stud + studPop + admission + graduation) / 4;
    }
}
