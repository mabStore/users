package org.example.users.services;

import org.example.users.dtos.UserDto;

import java.util.List;

public interface UserService {
    boolean createUser(UserDto userDTO);

    boolean isEmailAlreadyInUse(String email);

    List<UserDto> findAllUsers();
}
