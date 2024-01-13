package maks.ter.geocalc.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "directory_regions_and_cities")
public class DirectoryRegionsAndCities {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "code_number")
  private long codeNumber;

  @Column(name = "region")
  private String region;

  @Column(name = "fed_district")
  private String fedDistrict;

  @Column(name = "short_fed_district")
  private String shortFedDistrict;

  @Column(name = "city")
  private String city;
}
