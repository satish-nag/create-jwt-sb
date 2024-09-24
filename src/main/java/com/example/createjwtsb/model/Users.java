package com.example.createjwtsb.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.context.annotation.Primary;

@Data
@Entity
public class Users {
    @Id
    @Column(name = "id")
    public Integer id;
    @Column(name = "name")
    public String name;

    @Column(name = "role")
    public String role;
}
