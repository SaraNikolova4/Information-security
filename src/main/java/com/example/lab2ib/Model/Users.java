package com.example.lab2ib.Model;

import com.example.lab2ib.encrypt;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String username;
    private String email;
    private String password;
    private String cpassword;
    private String name;
    private String surname;
    private String phone;
    private String random;
    private Role role;
    public Users(String username, String email, String password, String name, String surname, String phone, Role role) {
        this.username = username;
        this.email = email;
        this.random = encrypt.randomstring(30);
        this.password = encrypt.generatespassword(password, this.random);
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.role =role;
    }
}
