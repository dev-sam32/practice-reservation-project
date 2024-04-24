package com.zerobase.reservation.controller;

import com.zerobase.reservation.dto.Auth.SignIn;
import com.zerobase.reservation.dto.Auth.SignUp;
import com.zerobase.reservation.entity.Member;
import com.zerobase.reservation.security.TokenProvider;
import com.zerobase.reservation.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {
    private final MemberService memberService;
    private final TokenProvider tokenProvider;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUp req) {
        Member member = this.memberService.register(req);

        log.info(String.format("Member SignUp!!! : %s", member.getName()));
        return ResponseEntity.ok(member);
    }

    @GetMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody SignIn req) {
        Member member = this.memberService.authentication(req);
        String token = this.tokenProvider.generateToken(member.getUsername(), member.getRole().getRoles());

        String resultMsg = req.partnerYn() ? "Partner SignIn!!!" : "User SignIn!!!";
        log.info(String.format("%s : %s", resultMsg, member.getName()));
        log.info(String.format("JWT Token : %s", token));
        return ResponseEntity.ok(token);
    }
}
