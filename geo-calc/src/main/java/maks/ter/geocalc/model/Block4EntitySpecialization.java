package maks.ter.geocalc.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "block4_entity_specialization")
public class Block4EntitySpecialization {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long codeNumber;
  private String region;
  private String specialization;
  private long vacancies;

}
