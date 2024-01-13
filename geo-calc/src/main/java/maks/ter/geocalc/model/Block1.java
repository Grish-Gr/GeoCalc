package maks.ter.geocalc.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "block1")
public class Block1 {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Код")
    private long codeNumber;
    @Column(name = "Region")
    private String region;

    @Column(name = "Year_res")
    private long yearRes;
    @Column(name = "Home_Pc")
    private double homePc;
    @Column(name = "Home_Int")
    private double homeInt;
    @Column(name = "Home_broadband")
    private double homeBroadband;
    @Column(name = "Pop_Int")
    private double popInt;
    @Column(name = "Pop_Intday")
    private double popIntday;
    @Column(name = "Subsr_mob")
    private double subsrMob;
    @Column(name = "Subsr_fix_broadband")
    private double subsrFixBroadband;
    @Column(name = "Subsr_mob_broadband")
    private double subsrMobBroadband;
}
