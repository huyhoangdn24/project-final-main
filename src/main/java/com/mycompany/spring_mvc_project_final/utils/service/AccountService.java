/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.spring_mvc_project_final.utils.service;

import com.mycompany.spring_mvc_project_final.utils.entities.AccountEntity;
import com.mycompany.spring_mvc_project_final.utils.enums.UserStatus;
import com.mycompany.spring_mvc_project_final.utils.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public AccountEntity getAccountByEmail(String email) {
        return accountRepository.findByEmailLikeAndStatusLike(email, UserStatus.ACTIVE);
    }
    public AccountEntity findByEmail(String email) {
        return accountRepository.findByEmail(email);
    }
}
