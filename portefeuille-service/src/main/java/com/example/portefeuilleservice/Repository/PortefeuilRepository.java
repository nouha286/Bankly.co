package com.example.portefeuilleservice.Repository;

import com.example.portefeuilleservice.Model.Portefeuil;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PortefeuilRepository extends JpaRepository<Portefeuil,Long>
{


   Optional<Portefeuil> findPortefeuilByReference(String ref);

   Optional<Portefeuil> findPortefeuilByProprietaire(String proprietaire);


}
