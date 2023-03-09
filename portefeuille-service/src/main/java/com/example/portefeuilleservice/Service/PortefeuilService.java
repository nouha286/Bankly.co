package com.example.portefeuilleservice.Service;

import com.example.portefeuilleservice.Model.Portefeuil;

import java.util.List;
import java.util.Optional;

public interface PortefeuilService {

    public Portefeuil save(Portefeuil portefeuil);
    public Portefeuil update(Portefeuil portefeuil);
    public String delete(Long id);
    public List<Portefeuil> findAllPortefeuil();
    public Optional<Portefeuil> findPortefeuilByReference(String ref);
    Optional<Portefeuil> findPortefeuilByProprietaire(String proprietaire);

    String findReferenceOfPortefeuilByProprietaire(String proprietaire);
}
