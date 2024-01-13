package maks.ter.geocalc.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "block4_capital_specialization")
public class Block4CapitalSpecialization {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "code_number")
  private long codeNumber;

  @Column(name = "Region")
  private String region;

  @Column(name = "City")
  private String city;

  @Column(name = "specialization")
  private String specialization;

  @Column(name = "vacancies")
  private long vacancies;
}
