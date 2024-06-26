package com.bside.backendapi.domain.member.domain.persist;

import com.bside.backendapi.domain.member.domain.vo.Email;
import com.bside.backendapi.global.security.principal.CustomUserDetails;

import java.util.Optional;

public interface MemberCustomRepository {
    Optional<CustomUserDetails> findByIdWithDetails(final Long memberId);

    Optional<CustomUserDetails> findUserDetailsByEmail(final Email email);
}
