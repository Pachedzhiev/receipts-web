package com.example.demo.web.controllers.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReceiptModel {
    private String name;
    private String ingredients;
    private String description;
}
