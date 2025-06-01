package com.fullstackalex.UserService.controllers;

import com.fullstackalex.UserService.models.AuthRequest;
import com.fullstackalex.UserService.models.User;
import com.fullstackalex.UserService.security.services.JwtService;
import com.fullstackalex.UserService.security.services.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

   private final UserInfoService service;

   private final JwtService jwtService;

   private final AuthenticationManager authenticationManager;


   @GetMapping("/welcome")
    public String welcome() {
       return "Hola, endpoint no seguro";
   }

   @PostMapping("/addNewUser")
    public String addNewUser(@RequestBody User user) {
       return service.addUser(user);
   }

   @GetMapping("/user/userProfile")
   public String userProfile() {
      return "userProfile";
   }

   @GetMapping("/admin/adminProfile")
   public String adminProfile() {
      return "adminProfile";
   }

   @PostMapping("/generateToken")
   public String authenticationAndGetToken(@RequestBody AuthRequest authRequest) {
      Authentication authentication = authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
      );

      if (authentication.isAuthenticated()) {
         return jwtService.generateToken(authRequest.getUsername());
      } else {
         throw new UsernameNotFoundException("Invalid user request!");
      }
   }

}
