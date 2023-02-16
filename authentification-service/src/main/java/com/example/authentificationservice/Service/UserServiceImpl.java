package com.example.authentificationservice.Service;



import com.example.authentificationservice.DTO.Operation;
import com.example.authentificationservice.DTO.Portefeuil;
import com.example.authentificationservice.DTO.UserDto;
import com.example.authentificationservice.Liaison.OperationRequiredRest;
import com.example.authentificationservice.Liaison.PortefeuilRequiredRest;
import com.example.authentificationservice.Model.User;
import com.example.authentificationservice.Repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    OperationRequiredRest operationRequiredRest;
    @Autowired
    PortefeuilRequiredRest portefeuilRequiredRest;
    String access="";

    @Value("${security.jwt.token.secret-key:secret-key}")
    private String secretKey;

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    private final UserRepository userRepository;



    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       Optional<User> user=userRepository.findUserByEmail(email);
       Collection<SimpleGrantedAuthority> authorities=new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("all"));


    return new org.springframework.security.core.userdetails.User(user.get().getEmail(),user.get().getPassword(),authorities);
    }

//-----------------------------------------------Inscription d'utilisateur----------------------------------------//
    @Override
    public User register(User user) {
        if (user.getEmail()!=null &&
            user.getFirstName()!=null &&
            user.getLastName()!=null &&
            user.getPassword()!=null)
        {
            if (userRepository.findUserByEmail(user.getEmail()).isPresent())
            {
                user.setMessage("il exist déja un compte avec cet email!");
                return user;
            }
            user.setMessage("success");
           return userRepository.save(user);

        }
        user.setMessage("failed");
        return user;
    }
 //----------------------------------------------Modification du compte utilisateur---------------------------------//

    @Override
    public User update(User user) {
        if (user.getId()==null)
        {
            user.setMessage("Il faut citer l'id d'utilisateur à modifier");
            return user;
        }
        Optional<User> userUpdated=userRepository.findById(user.getId());
        if (userUpdated.isPresent() )
        {
            if (user.getEmail()==null &&
                    user.getFirstName()==null &&
                    user.getLastName()==null &&
                    user.getPassword()==null)
            {
                userUpdated.get().setMessage("aucun information à modifier veuillez saisir un attribut");
                return userUpdated.get();
            }

            if (user.getPassword()!=null &&
                !userUpdated.get().getPassword().equals(user.getPassword()))
            {
                userUpdated.get().setPassword(user.getPassword());
            }

            if (user.getEmail()!=null &&
                !userUpdated.get().getEmail().equals(user.getEmail()))
            {
                userUpdated.get().setEmail(user.getEmail());
            }

            if (user.getFirstName()!=null &&
                !userUpdated.get().getFirstName().equals(user.getFirstName()))
            {
                userUpdated.get().setFirstName(user.getFirstName());
            }

            if(user.getLastName()!=null &&
               !userUpdated.get().getLastName().equals(user.getLastName()))
            {
                userUpdated.get().setLastName(user.getLastName());
            }

            userUpdated.get().setMessage("Success");
           return userRepository.save(userUpdated.get());

        }
        user.setMessage("le compte utilisateur que vous voulez modifier n'existe pas");
        return user;
    }

 //-----------------------------------------------Suppression d'un compte---------------------------------------------//

    @Override
    public String delete(Long id) {
        Optional<User> userDeleted=userRepository.findById(id);
        if (userDeleted.isPresent())
        {
            userRepository.delete(userDeleted.get());
            return "supprimé avec succés";
        }
        return "Le compte que vous voulez supprimer n'existe pas";

    }
//---------------------------------------------Afficher la liste des comptes------------------------------------------//

    @Override
    public List<User> findAllUsers() {

        return userRepository.findAll();
    }

    @Override
    public Operation passerOperation(Operation operation,String portefeuil) {

        operation.setPortefeuil(portefeuil);
        return operationRequiredRest.passerOperation(operation);
    }

    @Override
    public void setAccessToken(String access_token) {
        this.access=access_token;
    }

    @Override
    public String getAccessToken() {
        return this.access;
    }

    @Override
    public User registerAndCreatePortefeuil(UserDto userDto) {
        if (userDto.getEmail()!=null &&
                userDto.getFirstName()!=null &&
                userDto.getLastName()!=null &&
                userDto.getPassword()!=null)
        {
            if (userRepository.findUserByEmail(userDto.getEmail()).isPresent())
            {User user=new User();
                user.setMessage("il exist déja un compte avec cet email!");
                return user;
            }
            User user=new User();
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setEmail(userDto.getEmail());
            user.setPassword(userDto.getPassword());
            user.setMessage("success");
            userRepository.save(user);
            Portefeuil portefeuil=new Portefeuil();
            portefeuil.setProprietaire(userDto.getEmail());
            portefeuil.setSolde(userDto.getSolde());
            portefeuilRequiredRest.save(portefeuil);
            return user;

        }
        User user=new User();
        user.setMessage("failed");
        return user;
    }


}
