package maks.ter.geocalc.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "block1")
public class Block1 {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long codeNumber;
    private String region;
    private long yearRes;
    private double homePc;
    private double homeInt;
    private double homeBroadband;
    private double popInt;
    private double popIntday;
    private double subsrMob;
    private double subsrFixBroadband;
    private double subsrMobBroadband;
}
