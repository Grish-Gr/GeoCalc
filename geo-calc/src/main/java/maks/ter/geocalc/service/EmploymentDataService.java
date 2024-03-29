package maks.ter.geocalc.service;

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

        List<EmploymentDetailsDto> result = new ArrayList<>();

        List<RegionData> dataList = employmentDto.getDataType() == EmploymentDataType.VACANCIES ?
                regionDataRepo.findAllByCategoryOrderByValueDesc("block4_vacancies") :
                regionDataRepo.findAllByCategoryOrderByValueDesc("block4_proposed_salary");


        List<String> regionNamesListData = new ArrayList<>();

        for (RegionData regionData: dataList) {

            if (regionNamesListData.size() >= employmentDto.getMaxResult()) {
                break;
            }

            if (!regionNamesListData.contains(regionData.getRegion())) {
                regionNamesListData.add(regionData.getRegion());
            }
        }

        int rate = 1;
        for (String region: regionNamesListData) {

            DirectoryRegionsAndCities cityInfo = regionsAndCitiesRepo.findByRegion(region);
            MonthDataDto monthDataDto = getMonthData(region);

            result.add(EmploymentDetailsDto.builder()
                .rate(rate)
                .region(region)
                .statusLaborMarket(getStatusLaborMarket(region))
                .statusLaborMarketCapital(getStatusLaborMarketCapital(cityInfo))
                .middleSalary(getMiddleSalary(region))
                .middleSalaryCapital(getMiddleSalaryCapital(cityInfo))
                .middleCountVacancy(getMiddleCountVacancy(region))
                .middleCountVacancyCapital(getMiddleCountVacancyCapital(cityInfo))
                .middleCountVacancyInd(getMiddleCountVacancyInd(region))
                .middleCountVacancyIndCapital(getMiddleCountVacancyIndCapital(cityInfo))
                .levelLive(getLevelLive(region))
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

        if (resultData.isEmpty()) {
            return null;
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

        if (resultData.isEmpty()) {
            return null;
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

        if (resultData.isEmpty()) {
            return null;
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

        if (resultData.isEmpty()) {
            return null;
        }

        return sum / resultData.size();
    }

    private Long getMiddleCountVacancy(String region) {

        List<RegionData> resultData = regionDataRepo.findAllByCategoryAndRegion("block4_vacancies", region).stream().
                filter(regionData -> regionData.getDate().isAfter(LocalDate.now().minusYears(1))).toList();

        Long sum = 0L;
        for (RegionData regionData : resultData) {
            sum += regionData.getValue();
        }

        if (resultData.isEmpty()) {
            return null;
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

        if (resultData.isEmpty()) {
            return null;
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

        if (resultData.isEmpty()) {
            return null;
        }

        return Math.sqrt(Math.abs(sum));
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

        if (resultData.isEmpty()) {
            return null;
        }

        return Math.sqrt(sum);
    }
}
