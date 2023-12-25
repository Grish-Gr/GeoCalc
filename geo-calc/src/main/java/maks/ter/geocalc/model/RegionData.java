package maks.ter.geocalc.model;

import liquibase.pro.packaged.C;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@Table(name = "region_data")
public class RegionData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "region")
    private String region;

    @Column(name = "category")
    private String category;

    @Column(name = "date_entry")
    private LocalDate date;

    @Column(name = "value")
    private Long value;
}
