package com.zerobase.reservation.repository;

import com.zerobase.reservation.entity.PartnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PartnerRepository extends JpaRepository<PartnerEntity, Long> {
    Optional<PartnerEntity> findByName(String name); // 아이디로 검색
    boolean existsByName(String name);  // 가입된 아이디 유무
}
