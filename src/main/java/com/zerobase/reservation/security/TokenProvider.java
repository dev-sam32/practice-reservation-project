package com.zerobase.reservation.security;

import com.zerobase.reservation.service.MemberService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class TokenProvider {

    private final MemberService memberService;

    @Value("${spring.security.jwt.secretkey}")
    private String secretKey;

    @Value("${spring.security.jwt.expiredtime}")
    private long TOKEN_EXPIRED_TIME;

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(this.secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Token 생성
     * @param username  계정
     * @return
     */
    public String generateToken(String username, String role) {
        Claims claims = Jwts.claims()
                .subject(username)
                .add("role", role)
                .build();

        return Jwts.builder()
                .claims(claims)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRED_TIME))
                .signWith(this.getSigningKey())
                .compact();
    }

    /** Token 검증
     *
     * @param token
     * @return
     */
    public boolean isTokenValid(String token) {
        // member의 이름과 token에서 추출 된 이름이 동일 && Token 유효 기간을 넘지 않으면
        return isTokenHasNameValid(token) && !isTokenExpired(token);
    }

    private boolean isTokenHasNameValid(String token) {
        return getNameFromToken(token).equals(getMemberFromToken(token).getUsername());
    }

    /** Token 유효 기간 검증
     *
     * @param token
     * @return
     */
    private boolean isTokenExpired(String token) {
        return getClaimsFromToken(token).getExpiration().before(new Date());
    }

    /**
     * Token에서 이름 추출
     * @param token
     * @return
     */
    public String getNameFromToken(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    private UserDetails getMemberFromToken(String token) {
        return memberService.loadUserByUsername(getNameFromToken(token));
    }

    /** Token에서 Claims 추출
     *
     * @param token
     * @return
     */
    private Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                    .verifyWith(this.getSigningKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = this.memberService.loadUserByUsername(this.getNameFromToken(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
}
