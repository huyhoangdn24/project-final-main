package com.mycompany.spring_mvc_project_final.utils.service;

import com.mycompany.spring_mvc_project_final.utils.repository.BookingDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingDetailsService {
    @Autowired
    private BookingDetailsRepository bookingDetailsRepository;

}
