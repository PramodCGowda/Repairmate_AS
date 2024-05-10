package com.backend.backend.dto;

import lombok.Data;

@Data
public class LoginDTO {
    private String password;
    private String email;
}