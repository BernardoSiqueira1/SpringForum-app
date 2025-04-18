package com.springforum.app.Modules.Authentication.Service;

import com.springforum.app.Modules.Authentication.DTOs.AuthenticationDetailsDTO;
import com.springforum.app.Modules.User.Model.User;
import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private GetUserDetails getUserDetails;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public AuthenticationDetailsDTO authenticateUser(String login, String password){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(login, password);

        var auth = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        User authUser = (User) auth.getPrincipal();

        String token = tokenService.generateToken(authUser);

        return new AuthenticationDetailsDTO(authUser.getUserId(), authUser.getUsername(), authUser.getUserEmail(), token);
    }

}
