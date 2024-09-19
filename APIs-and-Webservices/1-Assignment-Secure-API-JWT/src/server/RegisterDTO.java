package com.jrome.auth.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) representing the information for user registration.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {

    private String name;
    private String username;
    private String email;
    private String password;

}
