package com.jowynd.ecommerce.services;

import com.jowynd.ecommerce.domain.User;
import com.jowynd.ecommerce.dto.user.UserDTO;
import com.jowynd.ecommerce.dto.user.UserUpdateDTO;
import com.jowynd.ecommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void createUser(UserDTO dto) {
        User user = new User();
        user.setUsername(dto.username());
        user.setEmail(dto.email());
        user.setPassword(dto.password());

        userRepository.save(user);
    }

    public UserDTO findUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found"));

        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword()
        );
    }

    public List<UserDTO> findAllUsers() {
        List<User> list = userRepository.findAll();

        return list.stream()
                .map(user -> new UserDTO(
                        user.getId(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getPassword()
                )).collect(Collectors.toUnmodifiableList());
    }

    public User updateUser(Long id, UserUpdateDTO dto) {


        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User user = optionalUser.get();
                user.setUsername(dto.username());
                user.setEmail(dto.email());
                user.setPassword(dto.password());

                userRepository.save(user);

                return user;
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
