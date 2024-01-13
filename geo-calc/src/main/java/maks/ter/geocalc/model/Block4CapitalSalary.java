package maks.ter.geocalc.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "block4_capital_salary")
public class Block4CapitalSalary {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "code_number")
  private long codeNumber;

  @Column(name = "Region")
  private String region;

  @Column(name = "Proposed_salary_level")
  private String proposedSalaryLevel;

  @Column(name = "Vacancies")
  private long vacancies;
}
