package com.riwi.librosya.api.dto.response;

import java.util.List;

public class UserResponse {
    private Long id;

    private String username;

    private String password;

    private String email;

    private String fullName;

    private String role;

    private List<LoanBasicResponse> loans;

    private List<ReservationBasicResponse> reservations;
}
