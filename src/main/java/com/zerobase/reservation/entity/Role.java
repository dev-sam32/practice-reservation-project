package com.zerobase.reservation.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    USER("ROLE_USER"),
    PARTNER("ROLE_PARTNER"),
    ADMIN("ROLE_USER,ROLE_PARTNER,ROLE_ADMIN");

    private final String roles;
}
