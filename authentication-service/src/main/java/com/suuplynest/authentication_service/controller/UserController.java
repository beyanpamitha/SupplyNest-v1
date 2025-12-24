package com.suuplynest.authentication_service.controller;

import com.suuplynest.authentication_service.dto.UsersDto;
import com.suuplynest.authentication_service.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class UserController {

    private final AuthService authService;

    @PostMapping("register-new-user")
    public ResponseEntity<UsersDto> saveUser(@RequestBody UsersDto usersDto){
        UsersDto savedUser = authService.saveUser(usersDto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
}
