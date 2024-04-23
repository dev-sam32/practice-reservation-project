package com.zerobase.reservation.controller;

import com.zerobase.reservation.dto.Auth.SignIn;
import com.zerobase.reservation.dto.Auth.SignUp;
import com.zerobase.reservation.entity.Member;
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

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUp req) {
        Member result = this.memberService.register(req);

        log.info("Member SignUp!!!");
        return ResponseEntity.ok(result);
    }

    @GetMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody SignIn req) {
        Member result = this.memberService.authentication(req);

        log.info("Member SignIn!!!");
        return ResponseEntity.ok(result);
    }
}
