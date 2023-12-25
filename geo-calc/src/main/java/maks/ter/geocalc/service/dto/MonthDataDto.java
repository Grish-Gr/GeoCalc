package maks.ter.geocalc.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MonthDataDto {
    // Месячный доход жителя
    BigDecimal monthSalary;
    // Расходы на душу населений
    BigDecimal monthExpense;
    // Индексы потреб
    Double indexPrice;
    // Числ населения
    Long countPeopleMinSalary;
}
