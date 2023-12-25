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
public class EducationDataService {

    @Autowired
    private Block3DigitalEducationRepo block3DigitalEducationRepo;
    @Autowired
    private Block3GeneralTermsRepo block3GeneralTermsRepo;
    @Autowired
    private Block5Repo block5Repo;
    @Autowired
    private DataUtils dataUtils;

    public List<EducationDetailsDto> getDataList(EducationDto educationDto) {

        List<Block3DigitalEducation> dataList;
        Pageable pageable = PageRequest.of(0, educationDto.getMaxResult());

        if (educationDto.getPriority() == EdPriority.AMOUNT_CONTRACT) {
            dataList = block3DigitalEducationRepo.findByOrderByMinContractAsc(pageable);
        } else if (educationDto.getPriority() == EdPriority.CONTRACT_COUNT) {
            dataList = block3DigitalEducationRepo.findByOrderByContractCountDesc(pageable);
        } else {
            dataList = block3DigitalEducationRepo.findByOrderByBudgetCountDesc(pageable);
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

        while (dataList.isEmpty() || currentYear < 2000) {
            dataList = block3GeneralTermsRepo.findAllByYearResAndRegion(currentYear, region);
            currentYear--;
        }

        Double stud = dataUtils.getStandardDoubleValue(dataList.stream().map(Block3GeneralTerms::getStud).toList());
        Double studPop = dataUtils.getStandardLongValue(dataList.stream().map(Block3GeneralTerms::getStudPop).toList());
        Double admission = dataUtils.getStandardDoubleValue(dataList.stream().map(Block3GeneralTerms::getAdmission).toList());
        Double graduation = dataUtils.getStandardDoubleValue(dataList.stream().map(Block3GeneralTerms::getGraduation).toList());

        return (stud + studPop + admission + graduation) / 4;
    }

    private Double getLevelLive(String region) {

        long currentYear = Year.now().getValue() - 1;
        List<Block5> dataList = new ArrayList<>();

        while (dataList.isEmpty() || currentYear < 2000) {
            dataList = block5Repo.findAllByYearResAndRegion(currentYear, region);
            currentYear--;
        }

        Double realIncAver = dataUtils.getStandardLongValue(dataList.stream().map(Block5::getRealIncAver).toList());
        Double realIncInd = dataUtils.getStandardDoubleValue(dataList.stream().map(Block5::getRealIncInd).toList());
        Double autoPop = dataUtils.getStandardDoubleValue(dataList.stream().map(Block5::getAutoPop).toList());
        Double housingPop = dataUtils.getStandardDoubleValue(dataList.stream().map(Block5::getHousingPop).toList());
        Double popPoverty = dataUtils.getStandardDoubleValue(dataList.stream().map(Block5::getPopPoverty).toList());
        Double consumSpend = dataUtils.getStandardLongValue(dataList.stream().map(Block5::getConsumSpend).toList());
        Double housingSpend = dataUtils.getStandardDoubleValue(dataList.stream().map(Block5::getHousingSpend).toList());
        Double consumPriceInd = dataUtils.getStandardDoubleValue(dataList.stream().map(Block5::getConsumPriceInd).toList());
        Double indRealEst1 = dataUtils.getStandardDoubleValue(dataList.stream().map(Block5::getIndRealEst1).toList());

        return (realIncAver + realIncInd + autoPop + housingPop + popPoverty + consumSpend + housingSpend + consumPriceInd + indRealEst1) / 9;
    }

    private BigDecimal getMonthSalary(String region) {

        Optional<Block5> dataInfo = block5Repo.findByRegion(region);
        return dataInfo.map(block5 -> new BigDecimal(block5.getRealIncAver())).orElseGet(() -> new BigDecimal(0));
    }

    private MonthDataDto getMonthData(String region) {
        Optional<Block5> dataInfo = block5Repo.findByRegion(region);

        if (dataInfo.isEmpty()) {
            return new MonthDataDto();
        } else {
            return MonthDataDto.builder()
                    .monthSalary(new BigDecimal(dataInfo.get().getRealIncAver()))
                    .monthExpense(new BigDecimal(dataInfo.get().getConsumSpend()))
                    .indexPrice(dataInfo.get().getConsumPriceInd())
                    .countPeopleMinSalary((long) dataInfo.get().getPopPoverty())
                    .build();
        }
    }
}
