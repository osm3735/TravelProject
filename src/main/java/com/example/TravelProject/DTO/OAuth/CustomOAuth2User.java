package com.example.TravelProject.DTO.OAuth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class CustomOAuth2User implements OAuth2User {

    private final UserDTO userDTO;
    private final Map<String, Object> attributes;

    public CustomOAuth2User(UserDTO userDTO, Map<String, Object> attributes) {
        this.userDTO = userDTO;
        this.attributes = attributes;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList();
        collection.add(() -> userDTO.getRole());
        return collection;
    }

    @Override
    public String getName() {
        return userDTO.getName(); // 또는 email
    }

    public String getUsername() {
        return userDTO.getUsername();
    }

    public String getEmail() {
        return userDTO.getEmail();
    }
}
