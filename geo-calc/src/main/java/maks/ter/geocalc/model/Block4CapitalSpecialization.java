package maks.ter.geocalc.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "block4_capital_specialization")
public class Block4CapitalSpecialization {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long codeNumber;
  private String region;
  private String city;
  private String specialization;
  private long vacancies;
}
