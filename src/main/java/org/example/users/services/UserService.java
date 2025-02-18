package org.example.users.services;

import org.example.users.dtos.UserDto;

public interface UserService {
    boolean createUser(UserDto userDTO);

    boolean isEmailAlreadyInUse(String email);
}
