package com.example.lab2ib.Repository;

import com.example.lab2ib.Model.Users;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {


    Users findByEmailAndPassword(String email, String password);
    Optional<Users> findByEmail(String email);

    Users findById(Long id);
}
