package maks.ter.geocalc.service.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class MigrationInfoRegionDetailsDto {

    private Integer rate;
    private String region;

    private Double diRegion;
    private Double diPeople;
    private Double diBusiness;

    private Double diProfession;
    private Double allDiEducation;

    Double levelLive;
    BigDecimal monthSalary;
    BigDecimal monthExpense;
    Double indexPrice;
    Long countPeopleMinSalary;
}
