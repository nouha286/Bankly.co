package com.example.portefeuilleservice.Model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data

public class Portefeuil implements Serializable {

    @Transient
    private String message;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String reference;
    private String proprietaire;
    private Double solde;

}
