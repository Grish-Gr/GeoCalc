package maks.ter.geocalc.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "block2")
public class Block2 {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  private long codeNumber;
  private String region;
  private long yearRes;
  private double compPc;
  private double compServ;
  private double compNet;
  private double copmGlobnet;
  private double compInt;
  private double compBroadband;
  private double copmPcInt;
  private double compSed;
  private double compSedex;
}
