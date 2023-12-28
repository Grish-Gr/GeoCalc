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

    protected MonthDataDto getMonthData(String region) {
        Optional<Block5> dataInfo = block5Repo.findByRegionOrderByYearResDesc(region);

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
