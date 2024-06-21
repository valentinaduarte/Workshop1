package com.riwi.librosya.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserBasicResponse {
    
    private Long id;

    private String username;
    
    private String password;

    private String email;

    private String fullName;

    private String role;

}