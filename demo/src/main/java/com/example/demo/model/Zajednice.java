package com.example.demo.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;

@Data
@Entity
@Table(name = "zajednice")
public class Zajednice implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_zajednice", nullable = false)
    private Integer idZajednice;

    @Column(name = "datum_kreiranja")
    private Date datumKreiranja;

    @Column(name = "ime_zajednice")
    private String imeZajednice;

    @Column(name = "opis_zajednice")
    private String opisZajednice;

    @Column(name = "razlog_suspenzije")
    private Integer razlogSuspenzije;

    @Column(name = "suspendovana")
    private Boolean suspendovana;

}
