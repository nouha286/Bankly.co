package com.example.authentificationservice.Controller;


import com.example.authentificationservice.DTO.Operation;
import com.example.authentificationservice.DTO.Portefeuil;
import com.example.authentificationservice.DTO.UserDto;
import com.example.authentificationservice.Liaison.PortefeuilRequiredRest;
import com.example.authentificationservice.Model.User;
import com.example.authentificationservice.Repository.UserRepository;
import com.example.authentificationservice.Service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("Management/User")
@Slf4j


public class UserController {
    @Autowired
    PortefeuilRequiredRest portefeuilRequiredRest;

    @PostMapping("/passerMesOperations")
    public Operation passerOperation(@RequestBody Operation operation, Principal principal) {
        Optional<Portefeuil> portefeuil=portefeuilRequiredRest.findPortefeuilByProprietaire(principal.getName());
        System.out.println(principal.getName());
        System.out.println(portefeuil);
        return userService.passerOperation(operation, portefeuil.get().getReference());
    }





    @PostMapping("/registerAndCreatePortefeuil")
    public User registerAndCreatePortefeuil(@RequestBody UserDto userDto) {
        return userService.registerAndCreatePortefeuil(userDto);
    }

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/{email}")
    @ResponseBody
    public Optional<User> findUserByEmail(@PathVariable("email") String email) {
        return userService.findUserByEmail(email);
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("/update")
    public User update(@RequestBody User user) {
        return userService.update(user);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public String delete(@PathVariable("id") Long id) {
        return userService.delete(id);
    }

    @GetMapping("/AllUsers")
    @ResponseBody
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }


}
