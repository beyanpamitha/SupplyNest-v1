package com.supplynest.user_profile_service.service.impl;

import com.supplynest.user_profile_service.dto.PasswordUpdateDto;
import com.supplynest.user_profile_service.dto.UserProfileDto;
import com.supplynest.user_profile_service.dto.UserUpdateDto;
import com.supplynest.user_profile_service.entity.User;
import com.supplynest.user_profile_service.repository.UserProfileRepo;
import com.supplynest.user_profile_service.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepo userProfileRepo;
    private final ModelMapper modelMapper;

    @Override
    public UserProfileDto saveUser(UserProfileDto userProfileDto) {
        User user = modelMapper.map(userProfileDto,User.class);  //Converting user's data(Dto type) into Entity type, for save in the db.

        User saveUser = userProfileRepo.save(user);

        UserProfileDto savedUser = modelMapper.map(saveUser,UserProfileDto.class);  //Converting Entity type data into Dto type, for return to frontend.
        return savedUser;

    }

    @Override
    public UserProfileDto getUserById(Long id) {
        User user = userProfileRepo.findById(id)   //Checking the db to find relevant user and throwing not found error if a user does not exist.
                .orElseThrow(()->
                        new ResponseStatusException(
                                NOT_FOUND,
                                "User not found with id" + id
                        )
                );
        UserProfileDto receivedUser = modelMapper.map(user,UserProfileDto.class);
        return receivedUser;
    }

    @Override
    public UserUpdateDto updateUser(Long id, UserUpdateDto userUpdateDto) {
        //Checking if there is a user in the db
        User user = userProfileRepo.findById(id)
                .orElseThrow(()->
                        new ResponseStatusException(
                                NOT_FOUND,
                                "User not found with id" + id
                        )
                );

        //Updating user
        if (userUpdateDto.getUsername() != null) {
            user.setUsername(userUpdateDto.getUsername());
        }
        if (userUpdateDto.getEmail() != null) {
            user.setEmail(userUpdateDto.getEmail());
        }
        if (userUpdateDto.getRole() != null) {
            user.setRole(userUpdateDto.getRole());
        }
        if (userUpdateDto.getStatus() != null) {
            user.setStatus(userUpdateDto.getStatus());
        }

        //Saving updated user
        User updatedUser = userProfileRepo.save(user);

        return modelMapper.map(updatedUser,UserUpdateDto.class);
    }

    @Override
    public String updatePassword(Long id, PasswordUpdateDto passwordUpdateDto) {
        User user = userProfileRepo.findById(id)
                .orElseThrow(()->
                        new ResponseStatusException(
                                NOT_FOUND,
                                "User not found with id" + id
                        )
                );
        if (passwordUpdateDto.getPassword() != null){
            user.setPassword(passwordUpdateDto.getPassword());
        }

        User updatedUser = userProfileRepo.save(user);

        return "Your password is changed successfully";
    }

    @Override
    public UserProfileDto deleteUser(Long id) {
        User user = userProfileRepo.findById(id)
                .orElseThrow(()->
                        new ResponseStatusException(
                                NOT_FOUND,
                                "User not found with id" + id
                        )
                );

        userProfileRepo.delete(user); //Deleting the user

        return modelMapper.map(user,UserProfileDto.class);
    }
}
