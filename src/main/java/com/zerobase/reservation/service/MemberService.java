package com.zerobase.reservation.service;

import com.zerobase.reservation.dto.Auth.SignIn;
import com.zerobase.reservation.dto.Auth.SignUp;
import com.zerobase.reservation.entity.Member;
import com.zerobase.reservation.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Member register(SignUp signUp) {
        boolean exists = this.memberRepository.existsByName(signUp.name());

        if (!exists) {
                Member member = signUp.toEntity();
                member.setPassword(this.passwordEncoder.encode(signUp.password()));
                return this.memberRepository.save(member);
        } else {
            // TODO : Exception
            throw new RuntimeException("이미 존재하는 ID 입니다. -> " + signUp.name());
        }
    }

    public Member authentication(SignIn signIn) {
        Member member = (Member) loadUserByUsername(signIn.name());

        if (!this.passwordEncoder.matches(member.getPassword(), signIn.password())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        return member;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.memberRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("계정을 찾을 수 없습니다. -> " + username));
    }
}
