package com.example.webapp.serverapp.controllers;

import com.example.webapp.serverapp.models.Account;
import com.example.webapp.serverapp.models.JwtRequest;
import com.example.webapp.serverapp.responses.JwtResponse;
import com.example.webapp.serverapp.repositories.AccountRepository;
import com.example.webapp.serverapp.services.UserService;
import com.example.webapp.serverapp.utils.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtility jwtUtility;



    @GetMapping
    public List<Account> list() {
        return accountRepository.findAll();
    }

    @PostMapping("/login")
    public JwtResponse login(@RequestBody JwtRequest jwtRequest) throws Exception{
        try{
           Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getEmail(),
                            jwtRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Invalid Credentials", e);
        }

        final UserDetails userDetails
                = userService.loadUserByUsername(jwtRequest.getEmail());

        final String token
                = jwtUtility.generateToken(userDetails);

        return new JwtResponse(token);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody final Account registerParam) {
        Account registerEmail = accountRepository.findByEmail(registerParam.getEmail());
        if(registerEmail != null){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE) ;
        } else {
            accountRepository.saveAndFlush(registerParam);
            return ResponseEntity.ok("200");
        }
    }
}
