package com.example.lab2ib.Service.impl;

import com.example.lab2ib.Model.Role;
import com.example.lab2ib.Model.Users;
import com.example.lab2ib.Repository.UserRepository;
import com.example.lab2ib.Service.UserService;
import com.example.lab2ib.encrypt;
import com.example.lab2ib.exception.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
public class UserServiceImpl implements UserService {

    public final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }


    public boolean check(String pass) {
        int proverka = 0;
        for (char c : pass.toCharArray()) {
            if (Character.isUpperCase(c)) {
                proverka++;
            }
        }
        for (char c : pass.toCharArray()) {
            if (c=='!' || c=='?' || c=='@' || c=='#') {
                proverka++;
            }
        }
        for (char c : pass.toCharArray()) {
            if (Character.isDigit(c)) {
                proverka++;
            }
        }
        if (proverka >= 3) return true;
        else return false;
    }
    public Users registration(String username, String email, String password, String rpassword, String name, String surname, String phone) {
        if (username.isEmpty() && email.isEmpty() || password.isEmpty() && name.isEmpty() && surname.isEmpty() && phone.isEmpty()) {
            throw new Everything();
        }
        if (username.isEmpty() || username == null) {
            throw new NoName();
        }
        if (password.isEmpty() || password == null) {
            throw new NoPassword();
        }
        if (name.isEmpty() || name == null) {
            throw new NoNameReal();
        }
        if (surname.isEmpty() || surname == null) {
            throw new NoSurname();
        }
        if (phone.isEmpty() || phone == null) {
            throw new Phone();
        }

        if (!Objects.equals(password, rpassword)) {
            System.out.println("Passwords is not same!");
        }
        boolean proveri = check(password);
        if (proveri) {
            Users novuser = new Users(username, email, password, name, surname, phone, Role.USER);
            return repository.save(novuser);
        } else {
            throw new lozinka();
        }


    }
    public boolean najava(String email, String password) {
        if (email.isEmpty() || email == null || password.isEmpty() || password == null) {
            throw new vnes();
        }
        String novpass = encrypt.generatespassword(password, repository.findByEmail(email).get().getRandom());
        Users s = repository.findByEmailAndPassword(email, novpass);
        if (s == null) {
            throw new nemakorisnik();
        }
        else
        {
            return true;
        }
    }

    public  List<Users> getAllUsers ()
    {
        return  repository.findAll();
    }
    public  void EditUser(long id, Role role)
    {
        Users user = repository.findById(id);
        user.setRole(role);
        repository.save(user);
    }

}
