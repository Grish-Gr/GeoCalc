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
public class EducationDetailsDto {

    Integer rate;
    String region;
    Long budgetCount;
    Long contractCount;
    BigDecimal minPriceContract;
    Double levelEducation;
    Double levelLive;
    BigDecimal monthSalary;
    BigDecimal monthExpense;
    Double indexPrice;
    Long countPeopleMinSalary;
}
