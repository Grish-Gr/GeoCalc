package maks.ter.geocalc.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "block2")
public class Block2 {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "Код")
  private long codeNumber;

  @Column(name = "Region")
  private String region;

  @Column(name = "Year_res")
  private long yearRes;

  @Column(name = "Comp_PC")
  private double compPc;

  @Column(name = "Comp_serv")
  private double compServ;

  @Column(name = "Comp_net")
  private double compNet;

  @Column(name = "Copm_globnet")
  private double copmGlobnet;

  @Column(name = "Comp_Int")
  private double compInt;

  @Column(name = "Comp_broadband")
  private double compBroadband;

  @Column(name = "Copm_PC_Int")
  private double copmPcInt;

  @Column(name = "Comp_sed")
  private double compSed;

  @Column(name = "Comp_sedex")
  private double compSedex;
}
