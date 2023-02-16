package com.example.authentificationservice.Liaison;


import com.example.authentificationservice.DTO.Portefeuil;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@FeignClient("Portefeuille-MicroService")
public interface PortefeuilRequiredRest {

    @GetMapping("Management/portefeuil/findByProprietaire/{proprietaire}")
    @ResponseBody
    Optional<Portefeuil> findPortefeuilByProprietaire(@PathVariable("proprietaire") String proprietaire);

    @RequestMapping( value = "/Management/portefeuil/create",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)

    Portefeuil save(Portefeuil portefeuil);
}
