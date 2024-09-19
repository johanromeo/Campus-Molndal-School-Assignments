package com.jrome.auth.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) representing the credentials for user login.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {

    private String usernameOrEmail;
    private String password;

}
