package maks.ter.geocalc.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "block3_digital_education")
public class Block3DigitalEducation {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long code_number;

  @Column(name = "education_level")
  private String educationLevel;

  private String program;

  @Column(name = "study_field")
  private String studyField;

  private String region;

  private String city;

  @Column(name = "study_direct")
  private java.sql.Date studyDirect;

  @Column(name = "min_contract")
  private long minContract;

  @Column(name = "budget_count")
  private long budgetCount;

  @Column(name = "contract_count")
  private long contractCount;
}
