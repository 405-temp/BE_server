package com.bside.backendapi.domain.member.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Data
public class PrincipalDetail implements UserDetails, OAuth2User {

    private Member member;
    private Collection<? extends GrantedAuthority> authorities;
    private Map<String, Object> attributes;

    public PrincipalDetail(Member member, Collection<? extends GrantedAuthority> authorities) {
        this.member = member;
        this.authorities = authorities;
    }

    public PrincipalDetail(Member member,
                           Collection<? extends GrantedAuthority> authorities,
                           Map<String, Object> attributes) {
        this.member = member;
        this.authorities = authorities;
        this.attributes = attributes;
    }

    // info -> 토큰에 들어갈 데이터
    public Map<String, Object> getMemberInfo() {
        Map<String, Object> info = new HashMap<>();
        info.put("username", member.getUsername());
        info.put("email", member.getEmail());
        info.put("role", member.getRole());
        info.put("profileImg", member.getProfileImg());
        info.put("thumbnailImg", member.getThumbnailImg());
        return info;
    }

    @Override
    public String getName() {
        return member.getEmail();
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
