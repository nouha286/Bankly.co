package com.example.portefeuilleservice.Controller;


import com.example.portefeuilleservice.Model.Portefeuil;
import com.example.portefeuilleservice.Service.PortefeuilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("Management/portefeuil")

public class PortefeuilController {



    @Autowired
    PortefeuilService portefeuilService;


    @GetMapping("findByProprietaire/{proprietaire}")
    @ResponseBody
    public Optional<Portefeuil> findPortefeuilByProprietaire(@PathVariable("proprietaire") String proprietaire) {
        return portefeuilService.findPortefeuilByProprietaire(proprietaire);
    }

    @GetMapping("/{ref}")
    @ResponseBody
    public Optional<Portefeuil>findPortefeuilByReference(@PathVariable("ref") String ref) {
        return portefeuilService.findPortefeuilByReference(ref);
    }




    @RequestMapping( value = "/create",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Portefeuil save(@RequestBody Portefeuil portefeuil)
    {
        return portefeuilService.save(portefeuil);
    }


    @RequestMapping(value = "/update",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Portefeuil update(@RequestBody Portefeuil portefeuil) {
        return portefeuilService.update(portefeuil);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        return portefeuilService.delete(id);
    }

    @GetMapping("/AllPortefeuil")
    public List<Portefeuil> findAllPortefeuil() {
        return portefeuilService.findAllPortefeuil();
    }

}
