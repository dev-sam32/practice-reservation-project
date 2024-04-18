package com.zerobase.reservation.controller;

import com.zerobase.reservation.dto.Auth.SignIn;
import com.zerobase.reservation.dto.Auth.SignUp;
import com.zerobase.reservation.entity.MemberEntity;
import com.zerobase.reservation.entity.PartnerEntity;
import com.zerobase.reservation.service.MemberService;
import com.zerobase.reservation.service.PartnerService;
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
    private final PartnerService partnerService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUp req) {
        if(req.partnerYn()) {
            PartnerEntity result = this.partnerService.register(req);
            log.info("Partner SignUp!!!");
            return ResponseEntity.ok(result);
        } else {
            MemberEntity result = this.memberService.register(req);
            log.info("Member SignUp!!!");
            return ResponseEntity.ok(result);
        }
    }

    @GetMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody SignIn req) {
        if(req.partnerYn()) {
            PartnerEntity result = this.partnerService.authentication(req);

            log.info("Partner SignIn!!!");
            return ResponseEntity.ok(result);
        } else {
            MemberEntity result = this.memberService.authentication(req);

            log.info("Member SignIn!!!");
            return ResponseEntity.ok(result);
        }
    }
}
