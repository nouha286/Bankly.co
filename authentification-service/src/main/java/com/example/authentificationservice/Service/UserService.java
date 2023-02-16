package com.example.authentificationservice.Service;

import com.example.authentificationservice.DTO.Operation;
import com.example.authentificationservice.DTO.UserDto;
import com.example.authentificationservice.Model.User;

import java.util.List;

public interface UserService {

    public User register(User user);
    public User update(User user);
    public String delete(Long id);
    public List<User> findAllUsers();

    public Operation passerOperation(Operation operation, String portefeuil);


    void setAccessToken(String access_token);
    String getAccessToken( );

    User registerAndCreatePortefeuil(UserDto userDto);
}
