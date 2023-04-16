package com.example.expensetracker.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class AuthenticationResponse {

    private String token;
    private Date expiredAt;

}
