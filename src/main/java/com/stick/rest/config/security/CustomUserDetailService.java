package com.stick.rest.config.security;

import com.stick.rest.advice.exception.CEmailSigninFailedException;
import com.stick.rest.advice.exception.CUserNotFoundException;
import com.stick.rest.repository.UserJpaRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserJpaRepo userJpaRepo;

    public UserDetails loadUserByUsername(String userPk) {
        return userJpaRepo.findById(Long.valueOf(userPk)).orElseThrow(CUserNotFoundException::new);
    }
}
