package com.zerobase.reservation.dto.Auth;

public record SignIn(String name,
        String password,
        boolean partnerYn) {
}
