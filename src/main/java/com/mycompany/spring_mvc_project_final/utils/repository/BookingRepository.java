package com.mycompany.spring_mvc_project_final.utils.repository;

import com.mycompany.spring_mvc_project_final.utils.entities.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Integer> {
    List<BookingEntity> findByAccount_Id(Long accountId);
    BookingEntity findById(int bookingId);
}
