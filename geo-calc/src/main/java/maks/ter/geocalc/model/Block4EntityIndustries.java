package maks.ter.geocalc.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "block4_entity_industries")
public class Block4EntityIndustries {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "code_number")
  private long codeNumber;

  @Column(name = "region")
  private String region;

  @Column(name = "industries")
  private String industries;

  @Column(name = "vacancies")
  private long vacancies;

  @Column(name = "proposed_salary_level")
  private String proposedSalaryLevel;
}
