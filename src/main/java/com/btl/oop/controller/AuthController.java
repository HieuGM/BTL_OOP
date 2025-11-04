package com.btl.oop.controller;

import com.btl.oop.dto.LoginRequestDTO;
import com.btl.oop.dto.RegisterRequestDTO;
import com.btl.oop.exception.UsernameAlreadyExistsException;
import com.btl.oop.service.impl.AuthServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class AuthController {
    private final AuthServiceImpl authServiceImpl;


    @GetMapping("/")
    public String showIndex(@AuthenticationPrincipal Object me, Model model) {
        // me có thể là AppUserDetails nếu bạn khai báo
        model.addAttribute("me", me);
        return "index";                   // -> templates/index.html
    }

//    @GetMapping("/auth/login")
//    public String showLoginForm(@RequestParam(value = "error", required = false) String error, @RequestParam(value = "logout", required = false) String logout, Model model){
//        if(error != null)
//            model.addAttribute("msg", "Sai tài khoản hoặc mật khẩu ");
//        if(logout != null)
//            model.addAttribute("msg", "Bạn đã đăng xuất");
//        model.addAttribute("form", new LoginRequestDTO());
//        return "/auth/login";
//    }

    @GetMapping("/auth/login")
    public String showLoginForm(Model model){
        // Xóa hết @RequestParam và if(error), if(logout)

        // 1. Spring tự động thêm "successMessage" (từ flash) vào Model (nếu có)
        // 2. View (Thymeleaf) tự động đọc "param.error" và "param.logout"

        // Chúng ta CHỈ CẦN thêm 'form' rỗng
        model.addAttribute("form", new LoginRequestDTO());

        return "auth/login"; // hoặc "auth/login" nếu file của bạn nằm trong /templates/auth/login.html
    }

    @GetMapping("/auth/register")
    public String showRegisterForm(Model model){
        model.addAttribute("form", new RegisterRequestDTO());
        return "auth/register";
    }
    @PostMapping("/auth/register")
    public String doRegister(@Valid @ModelAttribute ("form") RegisterRequestDTO form, BindingResult br, RedirectAttributes ra){
        if (br.hasErrors()) return "auth/register";
        //authServiceImpl.register(form);
        ra.addFlashAttribute("successMessage", "Đăng ký thành công! Hãy đăng nhập.");
        return "redirect:/auth/login";
    }

}
