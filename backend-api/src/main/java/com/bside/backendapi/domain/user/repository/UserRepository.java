package com.bside.backendapi.domain.user.repository;

import com.bside.backendapi.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
    Optional<User> findByKakaoId(Long kakaoId);
}
