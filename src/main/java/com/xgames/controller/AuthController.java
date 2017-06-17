package com.xgames.controller;

import com.xgames.model.User;
import com.xgames.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login(@AuthenticationPrincipal org.springframework.security.core.userdetails.User user) {
        if (user != null) {
            return "redirect:/home";
        }
        return "login";
    }

    @GetMapping("/registration")
    public ModelAndView registration(User user) {
        ModelAndView mv = new ModelAndView("registration");
        mv.addObject(user);
        return mv;
    }

    @PostMapping("/registration")
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult, RedirectAttributes attributes) {
        if (userService.userAlreadyExists(user.getEmail())) {
            bindingResult.rejectValue("email", "error.user",
                    "Já existe um usuário com o email '" + user.getEmail() + "' cadastrado!");
        }
        if (bindingResult.hasErrors()) {
            return registration(user);
        }
        userService.saveUser(user);
        attributes.addFlashAttribute("message", "Cadastro efetuado com sucesso! Por favor, Efetue o login");
        return new ModelAndView("redirect:/login");
    }
}
