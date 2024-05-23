package org.zeraki.task.learninglanguagemoduleapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zeraki.task.learninglanguagemoduleapi.models.user.AppUser;
import org.zeraki.task.learninglanguagemoduleapi.service.UserService;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    @Operation(summary = "register a new user")
    public ResponseEntity<?>registerUser(@RequestBody AppUser appUser){
        try{
           return ResponseEntity.ok(userService.registerUser(appUser));
        }catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while registering user");
        }
    }
    @GetMapping("/getAll")
    @Operation(summary = "get all users")
    public ResponseEntity<?>getAllUsers(){
        try{
           return ResponseEntity.ok(userService.getAllUsers());
        }catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while retrieving users");
        }
    }
}
