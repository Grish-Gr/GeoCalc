package maks.ter.geocalc.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "block3_digital_education")
public class Block3DigitalEducation {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long codeNumber;
  private String educationLevel;
  private String program;
  private String studyField;
  private String region;
  private String city;
  private java.sql.Date studyDirect;
  private long minContract;
  private long budgetCount;
  private long contractCount;
}
