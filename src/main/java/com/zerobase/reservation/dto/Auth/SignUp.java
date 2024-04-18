package com.zerobase.reservation.dto.Auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zerobase.reservation.entity.MemberEntity;
import com.zerobase.reservation.entity.PartnerEntity;

public record SignUp(String name,String password, @JsonProperty("partnerYn") Boolean partnerYn) {

    public SignUp(String name, String password, Boolean partnerYn) {
        this.name = name;
        this.password = password;
        this.partnerYn = partnerYn;
    }

    public Object toEntity() {
        if (this.partnerYn) {
            return PartnerEntity.builder()
                    .name(this.name)
                    .password(this.password)
                    .build();
        } else {
            return MemberEntity.builder()
                    .name(this.name)
                    .password(this.password)
                    .build();
        }
    }
}
