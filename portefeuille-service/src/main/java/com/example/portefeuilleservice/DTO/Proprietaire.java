package com.example.portefeuilleservice.DTO;

import lombok.Data;

@Data
public class Proprietaire {
    private Long id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
}
