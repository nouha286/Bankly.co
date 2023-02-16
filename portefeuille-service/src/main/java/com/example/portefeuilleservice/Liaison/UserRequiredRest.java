package com.example.portefeuilleservice.Liaison;


import com.example.portefeuilleservice.DTO.Proprietaire;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@FeignClient(name = "Authentification-MicroService")
public interface UserRequiredRest {

    @GetMapping("/Management/User/{email}")
    @ResponseBody
    public Optional<Proprietaire> findProprietaireByEmail(@PathVariable("email") String email);

}
