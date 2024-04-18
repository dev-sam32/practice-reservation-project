package com.zerobase.reservation.service;

import com.zerobase.reservation.dto.Auth.SignIn;
import com.zerobase.reservation.dto.Auth.SignUp;
import com.zerobase.reservation.entity.PartnerEntity;
import com.zerobase.reservation.repository.PartnerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class PartnerService {
    private final PartnerRepository partnerRepository;

    // TODO : Exception
    public PartnerEntity register(SignUp member) {
        boolean exists = this.partnerRepository.existsByName(member.name());
        if (!exists) {
                PartnerEntity partnerEntity = (PartnerEntity) member.toEntity();
                partnerEntity.setPassword(member.password());

                return this.partnerRepository.save(partnerEntity);
        } else {
            throw new RuntimeException("이미 존재하는 ID 입니다." + member.name());
        }
    }

    // TODO : Exception
    public PartnerEntity authentication(SignIn signIn) {
        PartnerEntity partnerEntity = this.partnerRepository.findByName(signIn.name())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 ID 입니다."));

        if (!partnerEntity.getPassword().equals(signIn.password())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        return partnerEntity;
    }
}
