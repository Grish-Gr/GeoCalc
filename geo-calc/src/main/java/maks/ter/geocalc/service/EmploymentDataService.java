package maks.ter.geocalc.service;

import liquibase.pro.packaged.A;
import maks.ter.geocalc.dto.EdPriority;
import maks.ter.geocalc.dto.EducationDto;
import maks.ter.geocalc.dto.EmploymentDataType;
import maks.ter.geocalc.dto.EmploymentDto;
import maks.ter.geocalc.model.*;
import maks.ter.geocalc.repository.Block5Repo;
import maks.ter.geocalc.repository.CityDataRepo;
import maks.ter.geocalc.repository.DirectoryRegionsAndCitiesRepo;
import maks.ter.geocalc.repository.RegionDataRepo;
import maks.ter.geocalc.service.dto.EducationDetailsDto;
import maks.ter.geocalc.service.dto.EmploymentDetailsDto;
import maks.ter.geocalc.service.dto.MonthDataDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmploymentDataService extends DataService {

    private RegionDataRepo regionDataRepo;
    private CityDataRepo cityDataRepo;
    private DirectoryRegionsAndCitiesRepo regionsAndCitiesRepo;


    @Autowired
    public EmploymentDataService(DataUtils dataUtils,
                                 Block5Repo block5Repo,
                                 RegionDataRepo regionDataRepo,
                                 CityDataRepo cityDataRepo,
                                 DirectoryRegionsAndCitiesRepo regionsAndCitiesRepo
    ) {
        super(dataUtils, block5Repo);
        this.regionDataRepo = regionDataRepo;
        this.cityDataRepo = cityDataRepo;
        this.regionsAndCitiesRepo = regionsAndCitiesRepo;
    }

    public List<EmploymentDetailsDto> getDataList(EmploymentDto employmentDto) {

        Pageable pageable = PageRequest.of(0, employmentDto.getMaxResult());
        List<EmploymentDetailsDto> result = new ArrayList<>();

        List<RegionData> dataList = employmentDto.getDataType() == EmploymentDataType.VACANCIES ?
                regionDataRepo.findAllByCategoryOrderByValueDesc("block4_vacancies") :
                regionDataRepo.findAllByCategoryOrderByValueDesc("block4_proposed_salary");

        Map<String, List<RegionData>> dataListByRegion = dataList.stream().collect(
            Collectors.groupingBy(RegionData::getRegion)
        );

        int rate = 1;
        for (Map.Entry<String, List<RegionData>> regionData: dataListByRegion.entrySet()) {

            DirectoryRegionsAndCities cityInfo = regionsAndCitiesRepo.findByRegion(regionData.getKey());
            MonthDataDto monthDataDto = getMonthData(regionData.getKey());

            result.add(EmploymentDetailsDto.builder()
                .rate(rate)
                .region(regionData.getKey())
                .statusLaborMarket(getStatusLaborMarket(regionData.getKey()))
                .statusLaborMarketCapital(getStatusLaborMarketCapital(cityInfo))
                .middleSalary(getMiddleSalary(regionData.getKey()))
                .middleSalaryCapital(getMiddleSalaryCapital(cityInfo))
                .middleCountVacancy(getMiddleCountVacancy(regionData.getKey()))
                .middleCountVacancyCapital(getMiddleCountVacancyCapital(cityInfo))
                .middleCountVacancyInd(getMiddleCountVacancyInd(regionData.getKey()))
                .middleCountVacancyIndCapital(getMiddleCountVacancyIndCapital(cityInfo))
                .levelLive(getLevelLive(regionData.getKey()))
                .monthSalary(monthDataDto.getMonthSalary())
                .monthExpense(monthDataDto.getMonthExpense())
                .indexPrice(monthDataDto.getIndexPrice())
                .countPeopleMinSalary(monthDataDto.getCountPeopleMinSalary())
                .build()
            );

            rate++;
        }

        return result;
    }

    private Long getStatusLaborMarket(String region) {

        List<RegionData> resultData = regionDataRepo.findAllByCategoryAndRegion("block4_hh_index", region).stream().
                filter(regionData -> regionData.getDate().isAfter(LocalDate.now().minusYears(1))).toList();

        Long sum = 0L;
        for (RegionData regionData : resultData) {
            sum += regionData.getValue();
        }

        return sum / resultData.size();
    }

    private Long getStatusLaborMarketCapital(DirectoryRegionsAndCities regionData) {

        if (regionData == null) {
            return null;
        }

        List<CityData> resultData = cityDataRepo.findAllByCategoryAndCity("block4_hh_city", regionData.getCity()).stream().
                filter(cityData -> cityData.getDate().isAfter(LocalDate.now().minusYears(1))).toList();

        Long sum = 0L;
        for (CityData cityData : resultData) {
            sum += cityData.getValue();
        }

        return sum / resultData.size();
    }

    private Long getMiddleSalary(String region) {

        List<RegionData> resultData = regionDataRepo.findAllByCategoryAndRegion("block4_proposed_salary", region).stream().
                filter(regionData -> regionData.getDate().isAfter(LocalDate.now().minusYears(1))).toList();

        Long sum = 0L;
        for (RegionData regionData : resultData) {
            sum += regionData.getValue();
        }

        return sum / resultData.size();
    }

    private Long getMiddleSalaryCapital(DirectoryRegionsAndCities regionData) {

        if (regionData == null) {
            return null;
        }

        List<CityData> resultData = cityDataRepo.findAllByCategoryAndCity("block4_proposed_salary_city", regionData.getCity()).stream().
                filter(cityData -> cityData.getDate().isAfter(LocalDate.now().minusYears(1))).toList();

        Long sum = 0L;
        for (CityData cityData : resultData) {
            sum += cityData.getValue();
        }

        return sum / resultData.size();
    }

    private Long getMiddleCountVacancy(String region) {

        List<RegionData> resultData = regionDataRepo.findAllByCategoryAndRegion("block4_resumes", region).stream().
                filter(regionData -> regionData.getDate().isAfter(LocalDate.now().minusYears(1))).toList();

        Long sum = 0L;
        for (RegionData regionData : resultData) {
            sum += regionData.getValue();
        }

        return sum / resultData.size();
    }

    private Long getMiddleCountVacancyCapital(DirectoryRegionsAndCities regionData) {

        if (regionData == null) {
            return null;
        }

        List<CityData> resultData = cityDataRepo.findAllByCategoryAndCity("block4_vacancies_city", regionData.getCity()).stream().
                filter(cityData -> cityData.getDate().isAfter(LocalDate.now().minusYears(1))).toList();

        Long sum = 0L;
        for (CityData cityData : resultData) {
            sum += cityData.getValue();
        }

        return sum / resultData.size();
    }

    private Double getMiddleCountVacancyInd(String region) {

        List<RegionData> resultData = regionDataRepo.findAllByCategoryAndRegion("block4_resumes_ind", region).stream().
                filter(regionData -> regionData.getDate().isAfter(LocalDate.now().minusYears(1))).toList();

        Long sum = 1L;
        for (RegionData regionData : resultData) {
            sum *= regionData.getValue();
        }

        return Math.sqrt(sum);
    }

    private Double getMiddleCountVacancyIndCapital(DirectoryRegionsAndCities regionData) {

        if (regionData == null) {
            return null;
        }

        List<CityData> resultData = cityDataRepo.findAllByCategoryAndCity("block4_resumes_ind_city", regionData.getCity()).stream().
                filter(cityData -> cityData.getDate().isAfter(LocalDate.now().minusYears(1))).toList();

        Long sum = 1L;
        for (CityData cityData : resultData) {
            sum *= cityData.getValue();
        }

        return Math.sqrt(sum);
    }
}
