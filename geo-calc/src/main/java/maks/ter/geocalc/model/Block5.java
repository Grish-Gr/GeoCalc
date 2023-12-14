package maks.ter.geocalc.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "block5")
public class Block5 {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long codeNumber;
  private String region;
  private long yearRes;
  private long feeInt;
  private double realIncInd;
  private long realIncAver;
  private long realIncMed;
  private double popPoverty;
  private long consumSpend;
  private double autoPop;
  private double housingPop;
  private double housingSpend;
  private double consumPriceInd;
  private double indRealEst1;
}
