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
@Table(name = "city_data")
public class CityData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "city")
    private String city;

    @Column(name = "category")
    private String category;

    @Column(name = "date_entry")
    private LocalDate date;

    @Column(name = "value")
    private Long value;


    public CityData(String city, String category, LocalDate date, Long value) {
        this.city = city;
        this.category = category;
        this.date = date;
        this.value = value;
    }
}
