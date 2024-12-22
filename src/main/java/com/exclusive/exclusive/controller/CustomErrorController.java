package com.exclusive.exclusive.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError() {
        // Retourne le nom de la page personnalisée
        return "error";
    }

    // @Override
    public String getErrorPath() {
        return "/error";
    }
}
