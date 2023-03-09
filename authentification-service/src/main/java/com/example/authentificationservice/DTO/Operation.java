package com.example.authentificationservice.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Operation {
    private String id;
    private String portefeuil;
    private Double montant;
    private LocalDate date;
    private String message;
}
