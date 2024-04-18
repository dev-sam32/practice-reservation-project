package com.zerobase.reservation.service;

import com.zerobase.reservation.dto.Auth.SignIn;
import com.zerobase.reservation.dto.Auth.SignUp;
import com.zerobase.reservation.entity.MemberEntity;
import com.zerobase.reservation.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberEntity register(SignUp signUp) {
        boolean exists = this.memberRepository.existsByName(signUp.name());
        if (!exists) {
                MemberEntity memberEntity = (MemberEntity) signUp.toEntity();
                memberEntity.setPassword(signUp.password());

                return this.memberRepository.save(memberEntity);
        } else {
            // TODO : Exception
            throw new RuntimeException("이미 존재하는 ID 입니다." + signUp.name());
        }
    }

    public MemberEntity authentication(SignIn signIn) {
        MemberEntity memberEntity = this.memberRepository.findByName(signIn.name())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 ID 입니다."));

        if (!memberEntity.getPassword().equals(signIn.password())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        return memberEntity;
    }
}
