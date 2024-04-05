package com.mycompany.spring_mvc_project_final.utils.repository;

import com.mycompany.spring_mvc_project_final.utils.entities.SendMailEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SendMailRepository extends CrudRepository<SendMailEntity, Integer> {
}