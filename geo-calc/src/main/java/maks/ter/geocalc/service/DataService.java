package maks.ter.geocalc.service;

import maks.ter.geocalc.model.Block5;
import maks.ter.geocalc.repository.Block5Repo;
import maks.ter.geocalc.service.dto.MonthDataDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class DataService {

    protected DataUtils dataUtils;
    protected Block5Repo block5Repo;

    public DataService(DataUtils dataUtils, Block5Repo block5Repo) {
        this.dataUtils = dataUtils;
        this.block5Repo = block5Repo;
    }

    protected Double getLevelLive(String region) {

        long currentYear = Year.now().getValue() - 1;
        List<Block5> allData = block5Repo.findAll();
        List<Block5> dataList = new ArrayList<>();
        List<Block5> allDataListInYear = new ArrayList<>();

        while (dataList.isEmpty() && currentYear < 2018) {
            dataList = block5Repo.findAllByYearResAndRegion(currentYear, region);
            allDataListInYear = block5Repo.findAllByYearRes(currentYear);
            currentYear--;
        }

        if (dataList.isEmpty()) {
            return null;
        }

        Double realIncAver = dataUtils.getStandardLongValue(
            dataList.stream().map(Block5::getRealIncAver).toList(),
            allDataListInYear.stream().map(Block5::getRealIncAver).toList(),
            allData.stream().map(Block5::getRealIncAver).toList()
        );
        Double realIncInd = dataUtils.getStandardDoubleValue(
            dataList.stream().map(Block5::getRealIncInd).toList(),
            allDataListInYear.stream().map(Block5::getRealIncInd).toList(),
            allData.stream().map(Block5::getRealIncInd).toList()
        );
        Double autoPop = dataUtils.getStandardDoubleValue(
            dataList.stream().map(Block5::getAutoPop).toList(),
            allDataListInYear.stream().map(Block5::getAutoPop).toList(),
            allData.stream().map(Block5::getAutoPop).toList()
        );
        Double housingPop = dataUtils.getStandardDoubleValue(
            dataList.stream().map(Block5::getHousingPop).toList(),
            allDataListInYear.stream().map(Block5::getHousingPop).toList(),
            allData.stream().map(Block5::getHousingPop).toList()
        );
        Double popPoverty = dataUtils.getStandardDoubleValue(
            dataList.stream().map(Block5::getPopPoverty).toList(),
            allDataListInYear.stream().map(Block5::getPopPoverty).toList(),
            allData.stream().map(Block5::getPopPoverty).toList()
        );
        Double consumSpend = dataUtils.getStandardLongValue(
            dataList.stream().map(Block5::getConsumSpend).toList(),
            allDataListInYear.stream().map(Block5::getConsumSpend).toList(),
            allData.stream().map(Block5::getConsumSpend).toList()
        );
        Double housingSpend = dataUtils.getStandardDoubleValue(
            dataList.stream().map(Block5::getHousingSpend).toList(),
            allDataListInYear.stream().map(Block5::getHousingSpend).toList(),
            allData.stream().map(Block5::getHousingSpend).toList()
        );
        Double consumPriceInd = dataUtils.getStandardDoubleValue(
            dataList.stream().map(Block5::getConsumPriceInd).toList(),
            allDataListInYear.stream().map(Block5::getConsumPriceInd).toList(),
            allData.stream().map(Block5::getConsumPriceInd).toList()
        );
        Double indRealEst1 = dataUtils.getStandardDoubleValue(
            dataList.stream().map(Block5::getIndRealEst1).toList(),
            allDataListInYear.stream().map(Block5::getIndRealEst1).toList(),
            allData.stream().map(Block5::getIndRealEst1).toList()
        );

        return (realIncAver + realIncInd + autoPop + housingPop + popPoverty + consumSpend + housingSpend + consumPriceInd + indRealEst1) / 9;
    }

    protected MonthDataDto getMonthData(String region) {

        List<Block5> dataInfo = block5Repo.findAllByRegionOrderByYearResDesc(region);

        if (dataInfo.isEmpty()) {
            return new MonthDataDto();
        } else {
            return MonthDataDto.builder()
                    .monthSalary(new BigDecimal(dataInfo.get(0).getRealIncAver()))
                    .monthExpense(new BigDecimal(dataInfo.get(0).getConsumSpend()))
                    .indexPrice(dataInfo.get(0).getConsumPriceInd())
                    .countPeopleMinSalary((long) dataInfo.get(0).getPopPoverty())
                    .build();
        }
    }
}
