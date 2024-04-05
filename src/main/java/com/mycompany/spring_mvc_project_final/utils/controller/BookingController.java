package com.mycompany.spring_mvc_project_final.utils.controller;

import com.mycompany.spring_mvc_project_final.utils.entities.AccountEntity;
import com.mycompany.spring_mvc_project_final.utils.entities.CategoryEntity;
import com.mycompany.spring_mvc_project_final.utils.entities.RoomEntity;
import com.mycompany.spring_mvc_project_final.utils.service.AccountService;
import com.mycompany.spring_mvc_project_final.utils.service.CategoryService;
import com.mycompany.spring_mvc_project_final.utils.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class BookingController {

    @Autowired
    private RoomService roomService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private CategoryService categoryService;

    // Test List danh sách phòng
//    @RequestMapping(value = {"/user/booking/{categoryName}"}, method = RequestMethod.GET)
//    public String showRoomsByCategory(@PathVariable String categoryName, Model model) {
//        List<RoomEntity> rooms = roomService.findAvailableRoomsByCategory(categoryName);
//        model.addAttribute("roomList", rooms);
//        model.addAttribute("categoryName", categoryName);
//        return "checkroom";
//    }

//    @RequestMapping(value = {"/search/{categoryName}"}, method = RequestMethod.GET)
//    public String showRooms(@PathVariable String categoryName, Model model) {
//        List<RoomEntity> rooms = roomService.findAvailableRoomsByCategory(categoryName);
//        model.addAttribute("roomList", rooms);
//        return "#";
//    }
@RequestMapping(value = {"/user/booking/{categoryName}"}, method = RequestMethod.GET)
public String getAvailableRooms(
        @PathVariable String categoryName,
        @RequestParam(name = "checkinDate") @DateTimeFormat(pattern = "dd-MM-yyyy") Date checkinDate,
        @RequestParam(name = "checkoutDate") @DateTimeFormat(pattern = "dd-MM-yyyy") Date checkoutDate,
        Model model) {


    List<RoomEntity> availableRooms = roomService.findAvailableRooms(categoryName, checkinDate, checkoutDate);
    model.addAttribute("availableRooms", availableRooms);
    model.addAttribute("categoryName", categoryName);
    return "checkroom";
}

    @RequestMapping(value = {"/user/infomation-{categoryName}"}, method = RequestMethod.GET)
    public String detailfamily(@PathVariable String categoryName, Model model, HttpSession session) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();

        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
            session.setAttribute("username", username);

        }
        String email = (String) session.getAttribute("username");
        session.setAttribute("categoryName", categoryName); //Lưu categoryName lại
        // Lấy thông tin phone, name dựa theo email
        AccountEntity account = accountService.findByEmail(email);
        model.addAttribute("name",account.getName());
        model.addAttribute("phone",account.getPhone());
        model.addAttribute("categoryName", categoryName);
        // Lấy giá phòng dựa theo categoryName
        CategoryEntity category = categoryService.findByName(categoryName);
        model.addAttribute("price",category.getPrice());
        model.addAttribute("priceVND",category.getPrice()*23000);
        return "reservation";
    }

}
