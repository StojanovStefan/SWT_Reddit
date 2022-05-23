package com.example.demo.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "korisnici")
public class Korisnici implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_korisnika", nullable = false)
    private Integer idKorisnika;

    @Column(name = "ime")
    private String ime;

    @Column(name = "prezime")
    private String prezime;

    @Column(name = "email")
    private String email;

    @Column(name = "sifra")
    private String sifra;

}
