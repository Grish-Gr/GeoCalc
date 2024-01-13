package maks.ter.geocalc.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "block4_hh_index")
public class Block4HhIndex {


  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "Код")
  private long codeNumber;

  @Column(name = "Region")
  private String region;

  @Column(name = "январь 2019")
  private long January_2019;
  @Column(name = "февраль 2019")
  private long February_2019;
  @Column(name = "март 2019")
  private long March_2019;
  @Column(name = "апрель 2019")
  private long April_2019;
  @Column(name = "май 2019")
  private long May_2019;
  @Column(name = "июнь 2019")
  private long June_2019;
  @Column(name = "июль 2019")
  private long July_2019;
  @Column(name = "август 2019")
  private long August_2019;
  @Column(name = "сентябрь 2019")
  private long September_2019;
  @Column(name = "октябрь 2019")
  private long October_2019;
  @Column(name = "ноябрь 2019")
  private long November_2019;
  @Column(name = "декабрь 2019")
  private long December_2019;

  @Column(name = "январь 2020")
  private long January_2020;
  @Column(name = "февраль 2020")
  private long February_2020;
  @Column(name = "март 2020")
  private long March_2020;
  @Column(name = "апрель 2020")
  private long April_2020;
  @Column(name = "май 2020")
  private long May_2020;
  @Column(name = "июнь 2020")
  private long June_2020;
  @Column(name = "июль 2020")
  private long July_2020;
  @Column(name = "август 2020")
  private long August_2020;
  @Column(name = "сентябрь 2020")
  private long September_2020;
  @Column(name = "октябрь 2020")
  private long October_2020;
  @Column(name = "ноябрь 2020")
  private long November_2020;
  @Column(name = "декабрь 2020")
  private long December_2020;

  @Column(name = "январь 2021")
  private long January_2021;
  @Column(name = "февраль 2021")
  private long February_2021;
  @Column(name = "март 2021")
  private long March_2021;
  @Column(name = "апрель 2021")
  private long April_2021;
  @Column(name = "май 2021")
  private long May_2021;
  @Column(name = "июнь 2021")
  private long June_2021;
  @Column(name = "июль 2021")
  private long July_2021;
  @Column(name = "август 2021")
  private long August_2021;
  @Column(name = "сентябрь 2021")
  private long September_2021;
  @Column(name = "октябрь 2021")
  private long October_2021;
  @Column(name = "ноябрь 2021")
  private long November_2021;
  @Column(name = "декабрь 2021")
  private long December_2021;

  @Column(name = "январь 2022")
  private long January_2022;
  @Column(name = "февраль 2022")
  private long February_2022;
  @Column(name = "март 2022")
  private long March_2022;
  @Column(name = "апрель 2022")
  private long April_2022;
  @Column(name = "май 2022")
  private long May_2022;
  @Column(name = "июнь 2022")
  private long June_2022;
  @Column(name = "июль 2022")
  private long July_2022;
  @Column(name = "август 2022")
  private long August_2022;
  @Column(name = "сентябрь 2022")
  private long September_2022;
  @Column(name = "октябрь 2022")
  private long October_2022;
  @Column(name = "ноябрь 2022")
  private long November_2022;
  @Column(name = "декабрь 2022")
  private long December_2022;

  @Column(name = "январь 2023")
  private long January_2023;
  @Column(name = "февраль 2023")
  private long February_2023;
  @Column(name = "март 2023")
  private long March_2023;
  @Column(name = "апрель 2023")
  private long April_2023;
}
