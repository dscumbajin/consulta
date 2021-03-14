package com.dscumbajin.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "erubro")
public class Erubro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer coderub;
    private String codpro;
    private Double cdrealerub;
    private Double cdreferub;
    private Double cdstderub;
    private Integer statuserub;
    private String nombreprorub;
    private Integer nocolparerub;
    private Integer nocolinderub;
    private Integer numrecetaalterub;
}
