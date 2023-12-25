package maks.ter.geocalc.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "block3_general_terms")
public class Block3GeneralTerms {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "code_numbers")
  private long codeNumber;
  private String region;
  private long yearRes;
  private double stud;
  private long studPop;
  private double admission;
  private double graduation;
}
