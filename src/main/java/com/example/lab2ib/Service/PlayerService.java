package com.example.lab2ib.Service;

import com.example.lab2ib.Model.Player;

import java.util.List;

public interface PlayerService {

    List<Player> listAllPlayers();

    Player findById(Long id);

    Player create(String name, String bio, Double pointsPerGame);

    Player update(Long id, String name, String bio, Double pointsPerGame);

    Player delete(Long id);

    Player vote(Long id);
}