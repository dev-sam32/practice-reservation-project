package com.zerobase.reservation.service;

import com.zerobase.reservation.dto.Auth.SignIn;
import com.zerobase.reservation.dto.Auth.SignUp;
import com.zerobase.reservation.entity.Member;
import com.zerobase.reservation.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public Member register(SignUp signUp) {
        boolean exists = this.memberRepository.existsByName(signUp.name());
        if (!exists) {
                Member member = signUp.toEntity();
                member.setPassword(signUp.password());

                return this.memberRepository.save(member);
        } else {
            // TODO : Exception
            throw new RuntimeException("이미 존재하는 ID 입니다." + signUp.name());
        }
    }

    public Member authentication(SignIn signIn) {
        Member member = this.memberRepository.findByName(signIn.name())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 ID 입니다."));

        if (!member.getPassword().equals(signIn.password())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        return member;
    }
}
