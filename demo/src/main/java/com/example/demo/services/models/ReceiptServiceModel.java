package com.example.demo.services.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReceiptServiceModel extends BaseServiceModel{
    private String name;

    private String ingredients;

    private String description;
}
