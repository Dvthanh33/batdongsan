package com.javaweb.controller.web;
import com.javaweb.entity.RoleEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.repository.RoleRepository;
import com.javaweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Collections;

@Controller
public class RegisterController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @PostMapping("/register")
    public String registerUser(@RequestParam String username,
                               @RequestParam String password,
                               @RequestParam String confirmPassword
//                               @RequestParam String firstName,
//                               @RequestParam String lastName
    ) {
        if(!password.equals(confirmPassword)) {
            return "redirect:/trang-chu?error=password";
        }
        if(userRepository.findOneByUserName(username) != null) {
            return "redirect:/trang-chu?error=username";
        }
        UserEntity user = new UserEntity();
        user.setUserName(username);
        user.setFullName(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setStatus(1);
//        user.setFirstName(firstName);
//        user.setLastName(lastName);
        RoleEntity role = roleRepository.findOneByCode("USER");
        user.setRoles(Collections.singletonList(role));
        userRepository.save(user);
        return "redirect:/login?register=success";
    }
}