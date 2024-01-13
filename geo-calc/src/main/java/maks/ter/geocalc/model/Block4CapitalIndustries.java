package maks.ter.geocalc.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "block4_capital_industries")
public class Block4CapitalIndustries {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "Код")
  private long codeNumber;

  @Column(name = "Region")
  private String region;

  @Column(name = "City")
  private String city;

  @Column(name = "Industries")
  private String industries;

  @Column(name = "Vacancies")
  private long vacancies;
}
