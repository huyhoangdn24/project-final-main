package com.mycompany.spring_mvc_project_final.utils.controller;

import com.mycompany.spring_mvc_project_final.utils.entities.AccountEntity;
import com.mycompany.spring_mvc_project_final.utils.entities.RoleEntity;
import com.mycompany.spring_mvc_project_final.utils.enums.UserStatus;
import com.mycompany.spring_mvc_project_final.utils.repository.AccountRepository;
import com.mycompany.spring_mvc_project_final.utils.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashSet;
import java.util.Optional;

@Controller
public class RegistrationController {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping(value = "/dangky", method = RequestMethod.GET)
    public String showRegistrationForm(Model model) {
        model.addAttribute("account", new AccountEntity());
        return "dangky";
    }

    @RequestMapping(value = "/dangky", method = RequestMethod.POST)
    public String register(@ModelAttribute("account") AccountEntity newAccount, Model model) {
            if (accountRepository.existsByEmail(newAccount.getEmail())) {
                model.addAttribute("error", "Email already exists");
                return "dangky";
            } else {
                UserStatus activeStatus = UserStatus.ACTIVE;
                newAccount.setStatus(activeStatus);

                String encryptedPassword = bCryptPasswordEncoder.encode(newAccount.getPassword());
                newAccount.setPassword(encryptedPassword);

                AccountEntity savedAccount = accountRepository.save(newAccount);

                // Creating account_role relationship
                // Thêm user đã đăng ký vào ROLE_USER
                if (savedAccount.getUserRoles() == null) {
                    savedAccount.setUserRoles(new HashSet<>());
                }

                Optional<RoleEntity> roleOptional = roleRepository.findById(Long.valueOf(2));
                if (roleOptional.isPresent()) {
                    savedAccount.getUserRoles().add(roleOptional.get());
                    accountRepository.save(savedAccount);
                } else {
                    // Xử lý khi không tìm thấy role
                }

                return "redirect:/login";
            }
    }
}