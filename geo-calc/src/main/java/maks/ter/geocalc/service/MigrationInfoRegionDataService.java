package maks.ter.geocalc.service;

import maks.ter.geocalc.dto.MigrationInfoRegionDto;
import maks.ter.geocalc.model.Block1;
import maks.ter.geocalc.model.Block2;
import maks.ter.geocalc.model.Block5;
import maks.ter.geocalc.model.RegionData;
import maks.ter.geocalc.repository.Block1Repo;
import maks.ter.geocalc.repository.Block2Repo;
import maks.ter.geocalc.repository.Block5Repo;
import maks.ter.geocalc.repository.RegionDataRepo;
import maks.ter.geocalc.service.dto.EducationDetailsDto;
import maks.ter.geocalc.service.dto.MigrationInfoRegionDetailsDto;
import maks.ter.geocalc.service.dto.MonthDataDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Service
public class MigrationInfoRegionDataService extends DataService{

    private Block1Repo block1Repo;
    private Block2Repo block2Repo;
    private RegionDataRepo regionDataRepo;

    @Autowired
    public MigrationInfoRegionDataService(DataUtils dataUtils, Block5Repo block5Repo, Block1Repo block1Repo, Block2Repo block2Repo, RegionDataRepo regionDataRepo) {
        super(dataUtils, block5Repo);

        this.block1Repo = block1Repo;
        this.block2Repo = block2Repo;
        this.regionDataRepo = regionDataRepo;
    }

    public List<MigrationInfoRegionDetailsDto> getDataList(MigrationInfoRegionDto migrationInfoRegionDto) {

        List<Block1> dataList = block1Repo.findAll();
        List<MigrationInfoRegionDetailsDto> result = new ArrayList<>();
        int rate = 0;

        for (Block1 dataInfo: dataList) {

            if (result.size() >= migrationInfoRegionDto.getMaxResults()) {
                break;
            }

            if (result.stream().anyMatch(data -> data.getRegion().equals(dataInfo.getRegion()))) {
                continue;
            }

            rate++;

            Double diPeople = getDiPeople(dataInfo.getRegion());
            Double diBusiness = getDiBusiness(dataInfo.getRegion());
            Double diProfession = getDiProfession(dataInfo.getRegion());

            MonthDataDto monthData = getMonthData(dataInfo.getRegion());

            result.add(MigrationInfoRegionDetailsDto.builder()
                .rate(rate)
                .region(dataInfo.getRegion())
                .diRegion(diPeople != null && diBusiness != null && diProfession != null ? (diPeople + diBusiness + diProfession) / 3 : 0)
                .diPeople(diPeople)
                .diBusiness(diBusiness)
                .diProfession(diProfession)
                .levelLive(getLevelLive(dataInfo.getRegion()))
                .monthSalary(monthData.getMonthSalary())
                .monthSalary(monthData.getMonthExpense())
                .countPeopleMinSalary(monthData.getCountPeopleMinSalary())
                .indexPrice(monthData.getIndexPrice())
                .build()
            );
        }

        return result;
    }

    private Double getDiPeople(String region) {

        long currentYear = Year.now().getValue() - 1;

        List<Block1> allData1 = block1Repo.findAll();
        List<Block1> dataList1 = new ArrayList<>();
        List<Block1> dataListInYear1 = new ArrayList<>();

        while (dataList1.isEmpty() && currentYear < 2018) {
            dataList1 = block1Repo.findAllByRegionAndYearRes(region, currentYear);
            dataListInYear1 = block1Repo.findAllByRegion(region);

            currentYear--;
        }

        if (dataList1.isEmpty()) {
            return null;
        }

        Double homePC = dataUtils.getStandardDoubleValue(
            dataList1.stream().map(Block1::getHomePc).toList(),
            dataListInYear1.stream().map(Block1::getHomePc).toList(),
            allData1.stream().map(Block1::getHomePc).toList()
        );

        Double homeInt = dataUtils.getStandardDoubleValue(
            dataList1.stream().map(Block1::getHomeInt).toList(),
            dataListInYear1.stream().map(Block1::getHomeInt).toList(),
            allData1.stream().map(Block1::getHomeInt).toList()
        );

        Double homeBroadband = dataUtils.getStandardDoubleValue(
            dataList1.stream().map(Block1::getHomeBroadband).toList(),
            dataListInYear1.stream().map(Block1::getHomeBroadband).toList(),
            allData1.stream().map(Block1::getHomeBroadband).toList()
        );

        Double popInt = dataUtils.getStandardDoubleValue(
            dataList1.stream().map(Block1::getPopInt).toList(),
            dataListInYear1.stream().map(Block1::getPopInt).toList(),
            allData1.stream().map(Block1::getPopInt).toList()
        );

        Double popIntDay = dataUtils.getStandardDoubleValue(
            dataList1.stream().map(Block1::getPopIntday).toList(),
            dataListInYear1.stream().map(Block1::getPopIntday).toList(),
            allData1.stream().map(Block1::getPopIntday).toList()
        );

        Double subsrMob = dataUtils.getStandardDoubleValue(
            dataList1.stream().map(Block1::getSubsrMob).toList(),
            dataListInYear1.stream().map(Block1::getSubsrMob).toList(),
            allData1.stream().map(Block1::getSubsrMob).toList()
        );

        Double subsrMobBroadband = dataUtils.getStandardDoubleValue(
            dataList1.stream().map(Block1::getSubsrMobBroadband).toList(),
            dataListInYear1.stream().map(Block1::getSubsrMobBroadband).toList(),
            allData1.stream().map(Block1::getSubsrMobBroadband).toList()
        );

        Double subsrFixBroadband = dataUtils.getStandardDoubleValue(
            dataList1.stream().map(Block1::getSubsrFixBroadband).toList(),
            dataListInYear1.stream().map(Block1::getSubsrFixBroadband).toList(),
            allData1.stream().map(Block1::getSubsrFixBroadband).toList()
        );

        return homePC + homeInt + homeBroadband + ((popInt + popIntDay + subsrMob + subsrFixBroadband + subsrMobBroadband) / 8);
    }

    private Double getDiBusiness(String region) {

        long currentYear = Year.now().getValue() - 1;

        List<Block2> allData2 = block2Repo.findAll();
        List<Block2> dataList2 = new ArrayList<>();
        List<Block2> dataListInYear2 = new ArrayList<>();

        while (dataList2.isEmpty() && currentYear < 2018) {
            dataList2 = block2Repo.findAllByRegionAndYearRes(region, currentYear);
            dataListInYear2 = block2Repo.findAllByRegion(region);

            currentYear--;
        }

        if (dataList2.isEmpty()) {
            return null;
        }

        Double compPC = dataUtils.getStandardDoubleValue(
                dataList2.stream().map(Block2::getCompPc).toList(),
                dataListInYear2.stream().map(Block2::getCompPc).toList(),
                allData2.stream().map(Block2::getCompPc).toList()
        );

        Double compServ = dataUtils.getStandardDoubleValue(
                dataList2.stream().map(Block2::getCompServ).toList(),
                dataListInYear2.stream().map(Block2::getCompServ).toList(),
                allData2.stream().map(Block2::getCompServ).toList()
        );

        Double compNet = dataUtils.getStandardDoubleValue(
                dataList2.stream().map(Block2::getCompNet).toList(),
                dataListInYear2.stream().map(Block2::getCompNet).toList(),
                allData2.stream().map(Block2::getCompNet).toList()
        );

        Double copmGlobnet = dataUtils.getStandardDoubleValue(
                dataList2.stream().map(Block2::getCopmGlobnet).toList(),
                dataListInYear2.stream().map(Block2::getCopmGlobnet).toList(),
                allData2.stream().map(Block2::getCopmGlobnet).toList()
        );

        Double compInt = dataUtils.getStandardDoubleValue(
                dataList2.stream().map(Block2::getCompInt).toList(),
                dataListInYear2.stream().map(Block2::getCompInt).toList(),
                allData2.stream().map(Block2::getCompInt).toList()
        );

        Double compBroadband = dataUtils.getStandardDoubleValue(
                dataList2.stream().map(Block2::getCompBroadband).toList(),
                dataListInYear2.stream().map(Block2::getCompBroadband).toList(),
                allData2.stream().map(Block2::getCompBroadband).toList()
        );

        Double copmPcInt = dataUtils.getStandardDoubleValue(
                dataList2.stream().map(Block2::getCopmPcInt).toList(),
                dataListInYear2.stream().map(Block2::getCopmPcInt).toList(),
                allData2.stream().map(Block2::getCopmPcInt).toList()
        );

        Double compSed = dataUtils.getStandardDoubleValue(
                dataList2.stream().map(Block2::getCompSed).toList(),
                dataListInYear2.stream().map(Block2::getCompSed).toList(),
                allData2.stream().map(Block2::getCompSed).toList()
        );

        Double compSedex = dataUtils.getStandardDoubleValue(
                dataList2.stream().map(Block2::getCompSedex).toList(),
                dataListInYear2.stream().map(Block2::getCompSedex).toList(),
                allData2.stream().map(Block2::getCompSedex).toList()
        );


        return compPC + compServ + compNet + copmGlobnet + compInt + compBroadband + ((copmPcInt + compSed + compSedex) / 9);
    }
    
    private Double getDiProfession(String region) {

        long currentYear = Year.now().getValue() - 1;

        List<RegionData> allProposedSalary = regionDataRepo.findAllByCategoryAndRegion("block4_proposed_salary", region);
        List<RegionData> proposedSalary = new ArrayList<>();
        List<RegionData> proposedSalaryInYear = new ArrayList<>();

        for (RegionData regionData: allProposedSalary) {
            if (regionData.getDate().isAfter(LocalDate.now().minusYears(1))) {
                proposedSalaryInYear.add(regionData);

                if (regionData.getRegion().equals(region)) {
                    proposedSalary.add(regionData);
                }
            }
        }

        List<RegionData> allVacancies = regionDataRepo.findAllByCategoryAndRegion("block4_vacancies", region);
        List<RegionData> vacancies = new ArrayList<>();
        List<RegionData> vacanciesInYear = new ArrayList<>();

        for (RegionData regionData: allVacancies) {
            if (regionData.getDate().isAfter(LocalDate.now().minusYears(1))) {
                vacanciesInYear.add(regionData);

                if (regionData.getRegion().equals(region)) {
                    vacancies.add(regionData);
                }
            }
        }

        List<RegionData> allVacanciesInd = regionDataRepo.findAllByCategoryAndRegion("block4_vacancies_ind", region);
        List<RegionData> vacanciesInd = new ArrayList<>();
        List<RegionData> vacanciesIndInYear = new ArrayList<>();

        for (RegionData regionData: allVacanciesInd) {
            if (regionData.getDate().isAfter(LocalDate.now().minusYears(1))) {
                vacanciesIndInYear.add(regionData);

                if (regionData.getRegion().equals(region)) {
                    vacanciesInd.add(regionData);
                }
            }
        }

        List<RegionData> allHhIndex = regionDataRepo.findAllByCategoryAndRegion("block4_vacancies_ind", region);
        List<RegionData> hhIndex = new ArrayList<>();
        List<RegionData> hhIndexInYear = new ArrayList<>();

        for (RegionData regionData: allHhIndex) {
            if (regionData.getDate().isAfter(LocalDate.now().minusYears(1))) {
                hhIndexInYear.add(regionData);

                if (regionData.getRegion().equals(region)) {
                    hhIndex.add(regionData);
                }
            }
        }

        Double proposedSalaryVal = dataUtils.getStandardLongValue(
            proposedSalary.stream().map(RegionData::getValue).toList(),
            proposedSalaryInYear.stream().map(RegionData::getValue).toList(),
            allProposedSalary.stream().map(RegionData::getValue).toList()
        );

        Double vacanciesVal = dataUtils.getStandardLongValue(
                vacancies.stream().map(RegionData::getValue).toList(),
                vacanciesInYear.stream().map(RegionData::getValue).toList(),
                allVacancies.stream().map(RegionData::getValue).toList()
        );

        Double vacanciesIndVal = dataUtils.getStandardLongValue(
                vacanciesInd.stream().map(RegionData::getValue).toList(),
                vacanciesIndInYear.stream().map(RegionData::getValue).toList(),
                allVacanciesInd.stream().map(RegionData::getValue).toList()
        );

        Double hhIndexVal = dataUtils.getStandardLongValue(
                hhIndex.stream().map(RegionData::getValue).toList(),
                hhIndexInYear.stream().map(RegionData::getValue).toList(),
                allHhIndex.stream().map(RegionData::getValue).toList()
        );

        return (proposedSalaryVal + vacanciesVal + vacanciesIndVal + hhIndexVal) / 4;
    }
}
