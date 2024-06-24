package com.riwi.librosya.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    @NotBlank(message = "The user's username is required.")
    private String username;

    @NotBlank(message = "The user's password is required.")
    private String password;

    @Email(message = "The email is not valid.")
    private String email;

    @NotBlank(message = "The user's full name is required.")
    private String fullName;

    @NotNull(message = "The user role is required.")
    private String role;
}