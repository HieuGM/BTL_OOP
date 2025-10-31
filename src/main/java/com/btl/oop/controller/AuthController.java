//package com.btl.oop.controller;
//
//import com.btl.oop.dto.LoginForm;
//import com.btl.oop.dto.RegisterForm;
//import com.btl.oop.service.AuthService;
//import com.btl.oop.service.impl.AuthServiceImpl;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//@Controller
//@RequestMapping
//@RequiredArgsConstructor
//public class AuthController {
//    private final AuthServiceImpl authServiceImpl;
//
//
//    @GetMapping("/")
//    public String showIndex(@AuthenticationPrincipal Object me, Model model) {
//        // me có thể là AppUserDetails nếu bạn khai báo
//        model.addAttribute("me", me);
//        return "index";                   // -> templates/index.html
//    }
//
//    @GetMapping("/auth/login")
//    public String showLoginForm(@RequestParam(value = "error", required = false) String error, @RequestParam(value = "logout", required = false) String logout, Model model){
//        if(error != null)
//            model.addAttribute("msg", "Sai tài khoản hoặc mật khẩu ");
//        if(logout != null)
//            model.addAttribute("msg", "Bạn đã đăng xuất");
//        model.addAttribute("form", new LoginForm());
//        return "auth/login";
//    }
//
//    @GetMapping("/auth/register")
//    public String showRegisterForm(Model model){
//        model.addAttribute("form", new RegisterForm());
//        return "auth/register";
//    }
//    @PostMapping("/auth/register")
//    public String doRegister(@Valid @ModelAttribute ("form") RegisterForm form, BindingResult br, RedirectAttributes ra){
//        if (br.hasErrors()) return "auth/register";
//        authServiceImpl.register(form);
//        ra.addFlashAttribute("msg", "Đăng ký thành công! Hãy đăng nhập.");
//        return "redirect:/auth/login";
//    }
//
//}
