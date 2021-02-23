package com.stick.rest.controller;

import com.stick.rest.advice.exception.CEmailSigninFailedException;
import com.stick.rest.config.security.JwtTokenProvider;
import com.stick.rest.entity.User;
import com.stick.rest.model.request.UserSignRequest;
import com.stick.rest.model.response.CommonResult;
import com.stick.rest.model.response.SingleResult;
import com.stick.rest.repository.UserJpaRepo;
import com.stick.rest.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1")
public class SignController {

    private final UserJpaRepo userJpaRepo;
    private final JwtTokenProvider jwtTokenProvider;
    private final ResponseService responseService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping(value = "/signin")
    public SingleResult<String> signin(
            @RequestBody UserSignRequest request
            ) {
        User user = userJpaRepo.findByUid(request.getEmail())
                .orElseThrow(CEmailSigninFailedException::new);

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword()))
            throw new CEmailSigninFailedException();

        return responseService.getSingleResult(
                jwtTokenProvider.createToken(String.valueOf(user.getId()), user.getRoles())
        );
    }

    @PostMapping(value = "/signup")
    public CommonResult signup(
            @RequestParam String id,
            @RequestParam String password,
            @RequestParam String name
    ) {
        userJpaRepo.save(
                User.builder()
                    .uid(id)
                    .password(passwordEncoder.encode(password))
                    .name(name)
                    .roles(Collections.singletonList("ROLE_USER"))
                    .build()
        );

        return responseService.getSuccessResult();
    }
}
