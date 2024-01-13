package maks.ter.geocalc.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "block4_entity_specialization")
public class Block4EntitySpecialization {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "code_number")
  private long codeNumber;

  @Column(name = "region")
  private String region;

  @Column(name = "specialization")
  private String specialization;

  @Column(name = "vacancies")
  private long vacancies;

}
