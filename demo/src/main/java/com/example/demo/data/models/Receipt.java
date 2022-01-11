package com.example.demo.data.models;

import com.example.demo.data.models.base.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="receipts")
public class Receipt extends BaseEntity {

    @Column(name="name",nullable = false, length = 500)
    private String name;

    @Column(name="ingredients",nullable = false, length = 5000)
    private String ingredients;

    @Column(name="description",nullable = false, length = 5000)
    private String description;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name="user_id")
    private User user;


}
