package com.exclusive.exclusive.controller;

import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exclusive.dao.request.SignUpRequest;
import com.exclusive.dao.request.SigninRequest;
import com.exclusive.dao.response.JwtAuthenticationResponse;
import com.exclusive.exclusive.entity.Order;
import com.exclusive.exclusive.entity.User;
import com.exclusive.exclusive.repository.UserRepository;
import com.exclusive.exclusive.service.IAuthenticationService;
import com.exclusive.exclusive.service.IOrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public final class AuthController {

    private final IAuthenticationService authenticationService;
    private final IOrderService iOrderService;
    private final UserRepository userRepository;

    // @Autowired
    // AuthController() {}

    @PostMapping("/register")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigninRequest request) {
        JwtAuthenticationResponse jwtAuthenticationResponse = authenticationService.signin(request);

        Long cartId;
        Long userId;

        User autheUser = userRepository.findByEmail(request.getEmail()).get();
        System.out.println(autheUser.getId());
        List<Order> order = iOrderService.getOrderByUserId(autheUser.getId());
        cartId = order.get(0).getId();

        if (order.isEmpty()) {
            Order newOrder = new Order();
            newOrder.setUser(autheUser);
            newOrder.setOrder(false);
            newOrder.setValidated(false);
            iOrderService.AddOrder(newOrder);
            cartId = newOrder.getId();
        }

        jwtAuthenticationResponse.setCartId(cartId);

        return ResponseEntity.ok(jwtAuthenticationResponse);
    }

}
