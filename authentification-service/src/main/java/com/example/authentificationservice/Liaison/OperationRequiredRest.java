package com.example.authentificationservice.Liaison;


import com.example.authentificationservice.DTO.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("Management-operation-service")
public interface OperationRequiredRest {

    @RequestMapping( value = "/add",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)

    public Operation passerOperation(@RequestBody Operation operation) ;
}
