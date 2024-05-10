package com.backend.backend.dto;

import lombok.Data;

@Data
public class SignupDTO {
    private String password;
    private String email;

    private String username;
}
