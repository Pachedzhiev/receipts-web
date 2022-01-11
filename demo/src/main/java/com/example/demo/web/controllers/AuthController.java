package com.example.demo.web.controllers;

import com.example.demo.services.UserService;
import com.example.demo.services.models.UserServiceModel;
import com.example.demo.web.controllers.base.BaseController;
import com.example.demo.web.controllers.models.UserRegisterModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthController extends BaseController {
    private final ModelMapper modelMapper;
    private final UserService authService;

    @Autowired
    public AuthController(ModelMapper modelMapper, UserService authService) {
        this.modelMapper = modelMapper;
        this.authService = authService;
    }

    @GetMapping("/register")
    public ModelAndView getRegisterForm() {
        return new ModelAndView("auth/register");
    }

    @PostMapping("/register")
    public ModelAndView registerConfirm(@ModelAttribute UserRegisterModel model) {
        ModelAndView modelAndView = new ModelAndView();

        if (!model.getPassword().equals(model.getConfirmPassword())) {
            modelAndView.setViewName("/auth/register");
            return modelAndView;
        }

        UserServiceModel userO = this.modelMapper.map(model, UserServiceModel.class);

        this.authService.register(userO);

        modelAndView.setViewName("/auth/login");
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView getLoginPage(ModelAndView modelAndView) {
        modelAndView.setViewName("/auth/login");
        return modelAndView;
    }
}
