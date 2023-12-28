package maks.ter.geocalc.model;

import liquibase.pro.packaged.C;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "block3_general_terms")
public class Block3GeneralTerms {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "code_number")
  private long codeNumber;
  private String region;
  @Column(name = "year_res")
  private long yearRes;
  private double stud;
  @Column(name = "stud_pop")
  private long studPop;
  private double admission;
  private double graduation;
}
