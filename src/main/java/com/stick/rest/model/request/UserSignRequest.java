package com.stick.rest.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignRequest {
    private String email;
    private String password;
}
