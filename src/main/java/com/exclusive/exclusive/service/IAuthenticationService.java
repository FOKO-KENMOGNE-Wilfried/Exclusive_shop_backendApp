package com.exclusive.exclusive.service;

import com.exclusive.dao.request.SignUpRequest;
import com.exclusive.dao.request.SigninRequest;
import com.exclusive.dao.response.JwtAuthenticationResponse;

public interface IAuthenticationService {

    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);

}
