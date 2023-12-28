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
  @Column(name = "year_res")
  private long yearRes;
  @Column(name = "Fee_int")
  private long feeInt;
  @Column(name = "Real_inc_ind")
  private double realIncInd;
  @Column(name = "Real_inc_aver")
  private long realIncAver;
  @Column(name = "Real_inc_med")
  private long realIncMed;
  @Column(name = "Pop_poverty")
  private double popPoverty;
  @Column(name = "Consum_spend")
  private long consumSpend;
  @Column(name = "Auto_pop")
  private double autoPop;
  @Column(name = "Housing_pop")
  private double housingPop;
  @Column(name = "Housing_spend")
  private double housingSpend;
  @Column(name = "Consum_price_ind")
  private double consumPriceInd;
  @Column(name = "Ind_real_est1")
  private double indRealEst1;
}
