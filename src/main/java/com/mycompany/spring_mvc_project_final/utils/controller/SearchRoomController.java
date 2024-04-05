package com.mycompany.spring_mvc_project_final.utils.controller;


import com.mycompany.spring_mvc_project_final.utils.entities.CategoryEntity;
import com.mycompany.spring_mvc_project_final.utils.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class SearchRoomController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/checkAvailability")
    public String checkRoomAvailability(@RequestParam("adults") double numOfAdults,
                                        @RequestParam("children") double numOfChildren,
                                        Model model,
                                        RedirectAttributes redirectAttributes) {
        // Lấy danh sách các loại phòng phù hợp từ Service
        List<CategoryEntity> availableCategories = categoryService.getAvailableCategories(numOfAdults, numOfChildren);

        // Chuyển danh sách loại phòng qua trang kết quả
        model.addAttribute("category", availableCategories);

        return "rooms";
    }
}


