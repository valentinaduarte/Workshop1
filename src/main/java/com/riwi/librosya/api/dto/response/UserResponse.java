package com.riwi.librosya.api.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    
    private Long id;

    private String username;

    private String password;

    private String email;

    private String fullname;

    private String role;

    private List<BookBasicResponse> books;

    private List<LoanBasicResponse> loans;

    private List<ReservationBasicResponse> reservations;
}
