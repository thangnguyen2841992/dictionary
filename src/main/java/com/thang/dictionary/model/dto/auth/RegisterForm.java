package com.thang.dictionary.model.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterForm {
    private String username;

    private String password;

    private String confirmPassword;

    private String email;

    private String phone;

}
