package org.xocl404.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xocl404.UserDto;
import org.xocl404.service.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@SecurityRequirement(name = "basicAuth")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> create(@RequestParam("username") String username
            , @RequestParam("password") String password, @RequestParam Long ownerId){
        return ResponseEntity.ok(userService.registerUser(username, password, ownerId));
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> getCurrentUser(Principal principal) {
        return ResponseEntity.ok(userService.getByUsername(principal.getName()));
    }
}
