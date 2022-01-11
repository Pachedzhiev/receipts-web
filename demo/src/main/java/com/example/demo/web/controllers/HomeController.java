package com.example.demo.web.controllers;

import com.example.demo.services.ReceiptService;
import com.example.demo.services.models.ReceiptServiceModel;
import com.example.demo.web.controllers.base.BaseController;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController extends BaseController {
    private final ModelMapper mapper;
    private final ReceiptService receiptService;

    @Autowired
    public HomeController(ModelMapper mapper, ReceiptService receiptService) {
        this.mapper = mapper;
        this.receiptService = receiptService;
    }

    @GetMapping("/")
    public ModelAndView getIndex(ModelAndView modelAndView){
        List<ReceiptServiceModel> receipts = this.receiptService.findAll();
        modelAndView.addObject("receipts",receipts);
        return super.view("index",modelAndView);
    }

    @GetMapping("/admin_home")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView getIndexAdmin(ModelAndView modelAndView, Authentication principal){
        String user = principal.getName();
        List<ReceiptServiceModel> receipts = this.receiptService.getByUsername(user);
        modelAndView.addObject("receipts",receipts);
        return super.view("index_admin",modelAndView);
    }

    @GetMapping("/admin_all_receipts")
    public ModelAndView getAllReceipts(ModelAndView modelAndView){
        List<ReceiptServiceModel> receipts = this.receiptService.findAll();
        modelAndView.addObject("receipts",receipts);
        return super.view("all_receipts_admin",modelAndView);
    }



    @GetMapping("/home")
    public String getHome(){
        return "index_admin";
    }
}
