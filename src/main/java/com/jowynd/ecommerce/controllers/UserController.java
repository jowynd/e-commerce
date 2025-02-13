package com.jowynd.ecommerce.controllers;

import com.jowynd.ecommerce.dto.user.UserDTO;
import com.jowynd.ecommerce.dto.user.UserUpdateDTO;
import com.jowynd.ecommerce.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity createUser(@Valid @RequestBody UserDTO dto) {
        userService.createUser(dto);

        return ResponseEntity.ok().body("User created!");
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity findUserById(@PathVariable @Valid Long id) {
        UserDTO dto = userService.findUserById(id);

        return ResponseEntity.ok().body(dto);
    }

    @GetMapping
    public ResponseEntity findAllUsers() {
        List<UserDTO> list = userService.findAllUsers();

        return ResponseEntity.ok().body(list);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity updateUser(@PathVariable Long id, @RequestBody UserUpdateDTO dto) {
        userService.updateUser(id, dto);

        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteUserById(@PathVariable Long id) {
        userService.deleteById(id);

        return ResponseEntity.ok().body("User deleted with success!");
    }
}
