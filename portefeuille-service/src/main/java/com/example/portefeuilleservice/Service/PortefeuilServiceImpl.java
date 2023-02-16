package com.example.portefeuilleservice.Service;


import com.example.portefeuilleservice.DTO.Proprietaire;
import com.example.portefeuilleservice.Liaison.UserRequiredRest;
import com.example.portefeuilleservice.Model.Portefeuil;
import com.example.portefeuilleservice.Repository.PortefeuilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PortefeuilServiceImpl implements PortefeuilService {

    @Autowired
    PortefeuilRepository portefeuilRepository;
    @Autowired
    UserRequiredRest userRequiredRest;

    //---------------------------------------------------Créer un portefeuil--------------------------------------------------//
    @Override
    public Portefeuil save(Portefeuil portefeuil) {


       if (portefeuil.getProprietaire()!=null &&
           portefeuil.getSolde()!=null )
       {
           Optional<Proprietaire> proprietaire=userRequiredRest.findProprietaireByEmail(portefeuil.getProprietaire());
           if ( proprietaire.isPresent())
           {
               portefeuil.setReference(UUID.randomUUID().toString());
               portefeuil.setMessage("Success");
               return portefeuilRepository.save(portefeuil);
           }
       }
       portefeuil.setMessage("Veuillez saisir tous les informations nécessaires et valides pour la création d'un portefeuil");
       return portefeuil;
    }

    //---------------------------------------------Modifier un portefeuil------------------------------------------------------//

    @Override
    public Portefeuil update(Portefeuil portefeuil) {
        if (portefeuil.getId()==null)
        {
            portefeuil.setMessage("veuillez saisir l'id du portefeuil que vous souhaitez la modifier");
            return portefeuil;
        }
        Optional<Portefeuil> portefeuilUpdated=portefeuilRepository.findById(portefeuil.getId());

        if(portefeuilUpdated.isPresent())
        {
            if (portefeuil.getProprietaire()!=null &&
                    !portefeuil.getProprietaire().equals(portefeuilUpdated.get().getProprietaire()))
            {
                Optional<Proprietaire> proprietaire=userRequiredRest.findProprietaireByEmail(portefeuil.getProprietaire());
                if (!proprietaire.isPresent())
                {
                    portefeuil.setMessage("le proprietaire que vous avez choisi n'existe pas");
                    return portefeuil;
                }
                portefeuilUpdated.get().setProprietaire(portefeuil.getProprietaire());
            }
            if (portefeuil.getSolde()!=null &&
                !portefeuilUpdated.get().getSolde().equals(portefeuil.getSolde()))
            {
                portefeuilUpdated.get().setSolde(portefeuil.getSolde());
            }
            portefeuil.setMessage("Success");
            return portefeuilRepository.save(portefeuilUpdated.get());

        }
        portefeuil.setMessage("Failed");
        return portefeuil;

    }

    //---------------------------------------------Supprimer un portefeuil----------------------------------------------------//
    @Override
    public String delete(Long id) {
        Optional<Portefeuil> portefeuilDeleted=portefeuilRepository.findById(id);
        if (portefeuilDeleted.isPresent())
        {
            portefeuilRepository.delete(portefeuilDeleted.get());
            return "supprimé avec succés";
        }
        return "L'id du portefeuil que vous voulez supprimé n'existe pas";
    }

    //--------------------------------------------Afficher la liste des portefeuil nécessaire-----------------------------------//

    @Override
    public List<Portefeuil> findAllPortefeuil() {
        return portefeuilRepository.findAll();
    }

    @Override
    public Optional<Portefeuil> findPortefeuilByReference(String ref) {
        return portefeuilRepository.findPortefeuilByReference(ref);
    }

    @Override
    public Optional<Portefeuil> findPortefeuilByProprietaire(String proprietaire) {
        return portefeuilRepository.findPortefeuilByProprietaire(proprietaire);
    }
}
