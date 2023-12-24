package maks.ter.geocalc.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
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
    private Date date;

    @Column(name = "value")
    private Long value;
}
