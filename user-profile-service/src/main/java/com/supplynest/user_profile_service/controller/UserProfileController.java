package com.supplynest.user_profile_service.controller;

import com.supplynest.user_profile_service.dto.PasswordUpdateDto;
import com.supplynest.user_profile_service.dto.UserProfileDto;
import com.supplynest.user_profile_service.dto.UserUpdateDto;
import com.supplynest.user_profile_service.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user-profile")
@RequiredArgsConstructor   //Constructor injection
public class UserProfileController {

    private final UserProfileService userProfileService; //Constructor injection

    @GetMapping("get-user-by-id")
    public ResponseEntity<UserProfileDto> getUserById(@RequestParam Long id){
        UserProfileDto receivedUser = userProfileService.getUserById(id);
        return new ResponseEntity<>(receivedUser, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserUpdateDto> updateUser(
            @PathVariable Long id,
            @RequestBody UserUpdateDto userUpdateDto
    ){
        UserUpdateDto updatedUser = userProfileService.updateUser(id, userUpdateDto);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @PutMapping("password/{id}")
    public String updatePassword(
            @PathVariable Long id,
            @RequestBody PasswordUpdateDto passwordUpdateDto
            ){
        String message = userProfileService.updatePassword(id, passwordUpdateDto);
        return message;
    }

    @DeleteMapping("{id}")
    public ResponseEntity<UserProfileDto> deleteUser(@PathVariable Long id){
        UserProfileDto deletedUSer = userProfileService.deleteUser(id);
        return new ResponseEntity<>(deletedUSer, HttpStatus.OK);
    }

}
