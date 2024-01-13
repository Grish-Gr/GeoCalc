package maks.ter.geocalc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
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

    public RegionData(String region, String category, LocalDate date, Long value) {
        this.region = region;
        this.category = category;
        this.date = date;
        this.value = value;
    }
}
