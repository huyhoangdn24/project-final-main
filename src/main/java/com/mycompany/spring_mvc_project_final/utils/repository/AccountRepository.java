/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.spring_mvc_project_final.utils.repository;


import com.mycompany.spring_mvc_project_final.utils.entities.AccountEntity;
import com.mycompany.spring_mvc_project_final.utils.enums.UserStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends CrudRepository<AccountEntity, Long> {

    AccountEntity findByEmailLikeAndStatusLike(String email,
                                               UserStatus status);
    boolean existsByEmail(String email);
    AccountEntity findByEmail(String email);
    @Query("SELECT a.id FROM AccountEntity a WHERE a.email = :email")
    Long findAccountIdByEmail(@Param("email") String email);
    List<AccountEntity> findByEmailContaining(String searchUser);

}
