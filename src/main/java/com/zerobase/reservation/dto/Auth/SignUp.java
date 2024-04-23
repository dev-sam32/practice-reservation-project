package com.zerobase.reservation.dto.Auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zerobase.reservation.entity.Member;
import com.zerobase.reservation.entity.Role;

public record SignUp(String name, String password, @JsonProperty("partnerYn") Boolean partnerYn) {

    public SignUp(String name, String password, Boolean partnerYn) {
        this.name = name;
        this.password = password;
        this.partnerYn = partnerYn;
    }

    public Member toEntity() {
        return Member.builder()
                .name(this.name)
                .password(this.password)
                .role(partnerYn ? Role.PARTNER : Role.USER) // partnerYn 옵션을 통해서 PARTNER, USER Role 부여
                .build();
    }
}
