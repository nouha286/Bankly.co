package com.example.authentificationservice.DTO;

import lombok.Data;

@Data
public class Portefeuil {
    private String message;
    private Long id;
    private String reference;
    private String proprietaire;
    private Double solde;
}
