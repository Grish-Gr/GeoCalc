package maks.ter.geocalc.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "block4_entity_industries")
public class Block4EntityIndustries {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long codeNumber;
  private String region;
  private String industries;
  private long vacancies;
}
