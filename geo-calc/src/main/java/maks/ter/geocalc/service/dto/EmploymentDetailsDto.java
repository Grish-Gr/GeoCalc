package maks.ter.geocalc.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmploymentDetailsDto {
    Integer rate;
    String region;

    Long statusLaborMarket;
    Long statusLaborMarketCapital;

    Long middleSalary;
    Long middleSalaryCapital;

    Long middleCountVacancy;
    Long middleCountVacancyCapital;

    Double middleCountVacancyInd;
    Double middleCountVacancyIndCapital;

    Double levelLive;
    BigDecimal monthSalary;
    BigDecimal monthExpense;
    Double indexPrice;
    Long countPeopleMinSalary;
}
