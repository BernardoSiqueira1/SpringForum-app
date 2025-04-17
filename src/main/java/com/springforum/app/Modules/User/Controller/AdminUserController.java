package com.springforum.app.Modules.User.Controller;

import com.springforum.app.Modules.User.DTOs.EditUserCredentialsDTO;
import com.springforum.app.Modules.User.Services.AdminUserServices;
import com.springforum.app.Modules.User.Services.UserServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminUserController {

    @Autowired
    private UserServices userServices;

    @Autowired
    private AdminUserServices adminUserServices;

    @PutMapping("/edit/{userId}")
    public ResponseEntity<?> editUserCredentials(@PathVariable Long userId, @RequestBody @Valid EditUserCredentialsDTO editCredentialsDTO){
        userServices.editUserCredentials(userId, editCredentialsDTO);

        return ResponseEntity.status(200).body("Usuário foi alterado com sucesso.");
    }

    @PutMapping("/promote/{userId}")
    public ResponseEntity<?> promoteUserRole(@PathVariable Long userId){
        adminUserServices.promoteUserToAdmin(userId);

        return ResponseEntity.status(200).body("Usuário foi promovido para administrador.");
    }

    @PutMapping("/suspend/{userId}")
    public ResponseEntity<?> suspendUser(@PathVariable Long userId){
        adminUserServices.suspendUser(userId);

        return ResponseEntity.status(200).body(String.format("Usuário id: %s  suspenso por tempo indeterminado.", userId));
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId){
        userServices.deleteUserById(userId);

        return ResponseEntity.status(200).body("O usuário foi deletado com sucesso.");
    }

}
