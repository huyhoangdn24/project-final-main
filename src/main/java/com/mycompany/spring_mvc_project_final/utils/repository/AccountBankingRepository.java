package com.mycompany.spring_mvc_project_final.utils.repository;

import com.mycompany.spring_mvc_project_final.utils.entities.AccountBankingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountBankingRepository extends JpaRepository<AccountBankingEntity, Integer> {

    // Tìm thông tin thanh toán bằng accountId
    AccountBankingEntity findByAccountId(Long accountId);
}
