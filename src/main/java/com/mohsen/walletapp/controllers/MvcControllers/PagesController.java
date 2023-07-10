package com.mohsen.walletapp.controllers.MvcControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/views")
public class PagesController {

    @GetMapping("/")
    public String homePage() {
        System.out.println("*************** HELLO ");
        return "home.html";
    }

}
