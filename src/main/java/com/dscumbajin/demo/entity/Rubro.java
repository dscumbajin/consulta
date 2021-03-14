package com.dscumbajin.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rubro")
public class Rubro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codsecrub;
    private String codrub;
    private String nombrerub;
    private String descrub;
    private Double rendrubreal;
    private Double rendrubref;
    private Double costorubreal;
    private Double costorubref;
    private Double costorubstd;
    private String padrerub;
    private String nivelrub;
    private Double cantrubreal;
    private Double cantrubref;
    private Double cantrubstd;
    private String codpro;
    private Integer statusrub;
    private Double cdrubreal;
    private Double cdrubref;
    private Double cdrubstd;
    private Integer coderub;
    private String codcc;
    private Integer codidgasto;
    private Integer ordenrub;
    private Integer codnodo;
    private String codigotemp;
}
