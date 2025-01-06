package br.com.devthiagoramon.restwithspringbootandjavaerutio.controller;

import br.com.devthiagoramon.restwithspringbootandjavaerutio.data.vo.v1.security.AccountCredentialsVO;
import br.com.devthiagoramon.restwithspringbootandjavaerutio.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Authentication")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @SuppressWarnings("rawTypes")
    @Operation(summary = "Authenticates a user and returns a token")
    @PostMapping("/signin")
    public ResponseEntity<?> signIn(
            @RequestBody AccountCredentialsVO data) {
        if (checkIfParamsIsNotNull(data)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                                 .body("Invalid client request");
        }
        var token = authService.signin(data);
        if (token == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                                 .body("Invalid client request");
        }
        return ResponseEntity.ok(token);
    }

    @SuppressWarnings("rawTypes")
    @Operation(summary = "Refresh token for authenticated user and returns a token")
    @PutMapping("/refresh/{username}")
    public ResponseEntity<?> refreshToken(
            @PathVariable("username") String username, @RequestHeader("Authorization") String refreshToken) {

        if (checkIfParamsIsNotNull(username, refreshToken)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                                 .body("Invalid client request");
        }

        var token = authService.refreshToken(username, refreshToken);
        return ResponseEntity.ok(token);
    }

    private static boolean checkIfParamsIsNotNull(String username,
                                      String refreshToken) {
        return refreshToken == null || refreshToken.isBlank() || username == null || username.isBlank();
    }

    private static boolean checkIfParamsIsNotNull(AccountCredentialsVO data) {
        return data == null || data.getUsername() == null || data.getUsername()
                                                                 .isBlank() || data.getPassword()
                                                                                   .isEmpty() || data.getPassword()
                                                                                                     .isBlank();
    }


}
