package com.example.lab2ib.Service;

import com.example.lab2ib.Model.Role;
import com.example.lab2ib.Model.Users;

import java.util.List;

public interface UserService {
    Users registration(String username, String email, String password, String rpassword, String name, String surname, String phone);
    boolean check(String password);
    boolean najava(String password, String email);

    List<Users> getAllUsers ();

    void EditUser(long id, Role role);



}
