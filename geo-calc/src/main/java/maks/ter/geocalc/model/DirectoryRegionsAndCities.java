package maks.ter.geocalc.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "dirctory_regions_and_cities")
public class DirectoryRegionsAndCities {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long codeNumber;
  private String region;
  private String fedDistrict;
  private String shortFedDistrict;
  private String city;
}
