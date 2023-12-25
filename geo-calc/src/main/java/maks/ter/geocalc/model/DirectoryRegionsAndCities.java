package maks.ter.geocalc.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "dirctory_regions_and_cities")
public class DirectoryRegionsAndCities {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "code_number")
  private long codeNumber;

  private String region;

  @Column(name = "fed_district")
  private String fedDistrict;

  @Column(name = "short_fed_district")
  private String shortFedDistrict;

  @Column(name = "city")
  private String city;
}
