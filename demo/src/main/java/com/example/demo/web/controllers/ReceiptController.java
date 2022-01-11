package com.example.demo.web.controllers;

import com.example.demo.services.ReceiptService;
import com.example.demo.services.models.ReceiptServiceModel;
import com.example.demo.web.controllers.models.ReceiptModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.core.Authentication;


import java.io.IOException;

@Controller
public class ReceiptController {
    private final ReceiptService receiptService;
    private final ModelMapper mapper;

    @Autowired
    public ReceiptController(ReceiptService receiptService, ModelMapper modelMapper) {
        this.receiptService = receiptService;
        this.mapper = modelMapper;
    }


    @PostMapping("/receipt/create")
    public String createEducation(@ModelAttribute ReceiptModel model, Authentication principal) throws IOException {
        String user = principal.getName();
        ReceiptServiceModel receipt=this.mapper.map(model, ReceiptServiceModel.class);
        this.receiptService.save(receipt, user);
        return "redirect:/admin_home";
    }

    @PostMapping("/receipt/delete/{id}")
    public String deleteReceipt(@PathVariable String id) throws Exception {
        this.receiptService.deleteReceipt(id);
        return "redirect:/admin_home";
    }
}
