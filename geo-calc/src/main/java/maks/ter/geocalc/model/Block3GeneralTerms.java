package maks.ter.geocalc.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "block3_general_terms")
public class Block3GeneralTerms {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "Код")
  private long codeNumber;

  @Column(name = "Region")
  private String region;

  @Column(name = "year_res")
  private long yearRes;

  @Column(name = "Stud")
  private double stud;

  @Column(name = "Stud_pop")
  private long studPop;

  @Column(name = "Admission")
  private double admission;

  @Column(name = "Graduation")
  private double graduation;
}
