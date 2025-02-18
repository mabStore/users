package org.example.users.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.users.dtos.UserDto;
import org.example.users.exceptions.users.EmailAlreadyInUseException;
import org.example.users.models.User;
import org.example.users.models.enums.Role;
import org.example.users.repositories.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public boolean createUser(UserDto userDto) {
        String email = userDto.getEmail();
        if (isEmailAlreadyInUse(email)) {
            throw new EmailAlreadyInUseException("Email already in use: " + email);
        }
        User user = buildUserFromDto(userDto);
        userRepository.save(user);
        log.info("Saving new user with : {}", email);
        return true;
    }

    @Override
    public boolean isEmailAlreadyInUse(String email) {
        boolean emailExists = userRepository.findByEmail(email).isPresent();
        if (emailExists) {
            log.warn("Email already in use: {}", email);
        }
        return emailExists;
    }

    private User buildUserFromDto(UserDto userDto) {
        return User.builder()
                .email(userDto.getEmail())
                .firstName(userDto.getFirstName())
                .secondName(userDto.getSecondName())
                .password(BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt()))
                .role(userDto.getRole() != null ? userDto.getRole() : Role.USER)
                .isActive(true)
                .build();
    }
}
