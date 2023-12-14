package maks.ter.geocalc.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "block4_expected_salary_city")
public class Block4ExpectedSalaryCity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long codeNumber;
  private String city;
  private long january2019;
  private long february2019;
  private long march2019;
  private long april2019;
  private long may2019;
  private long june2019;
  private long july2019;
  private long august2019;
  private long september2019;
  private long october2019;
  private long november2019;
  private long december2019;
  private long january2020;
  private long february2020;
  private long march2020;
  private long april2020;
  private long may2020;
  private long june2020;
  private long july2020;
  private long august2020;
  private long september2020;
  private long october2020;
  private long november2020;
  private long december2020;
  private long january2021;
  private long february2021;
  private long march2021;
  private long april2021;
  private long may2021;
  private long june2021;
  private long july2021;
  private long august2021;
  private long september2021;
  private long october2021;
  private long november2021;
  private long december2021;
  private long january2022;
  private long february2022;
  private long march2022;
  private long april2022;
  private long may2022;
  private long june2022;
  private long july2022;
  private long august2022;
  private long september2022;
  private long october2022;
  private long november2022;
  private long december2022;
}
