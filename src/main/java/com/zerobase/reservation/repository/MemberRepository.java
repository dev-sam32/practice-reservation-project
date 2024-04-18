package com.zerobase.reservation.repository;

import com.zerobase.reservation.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    Optional<MemberEntity> findByName(String name); // 아이디로 검색
    boolean existsByName(String name);  // 가입된 아이디 유무
}
