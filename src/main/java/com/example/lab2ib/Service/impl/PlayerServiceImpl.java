package com.example.lab2ib.Service.impl;

import com.example.lab2ib.Model.Player;
import com.example.lab2ib.Repository.PlayerRepository;
import com.example.lab2ib.Service.PlayerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PlayerServiceImpl  implements PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository){
        this.playerRepository = playerRepository;

    }
    @Override
    public List<Player> listAllPlayers() {
         return playerRepository.findAll();
    }

    @Override
    public Player findById(Long id) throws NoSuchElementException {
        return playerRepository.findById(id).orElseThrow(NoSuchElementException :: new);
    }

    @Override
    public Player create(String name, String bio, Double pointsPerGame) {
        return playerRepository.save(new Player(name,bio,pointsPerGame));
    }

    @Override
    public Player update(Long id, String name, String bio, Double pointsPerGame) throws NoSuchElementException {
        Player p = playerRepository.findById(id).orElseThrow(NoSuchElementException :: new);
        p.setName(name);
        p.setBio(bio);
        p.setPointsPerGame(pointsPerGame);
        playerRepository.save(p);
        return p;
    }

    @Override
    public Player delete(Long id) throws NoSuchElementException {
        Player p = playerRepository.findById(id).orElseThrow(NoSuchElementException :: new);
        playerRepository.delete(p);
        return  p;
    }

    @Override
    public Player vote(Long id) throws NoSuchElementException {
        Player p = playerRepository.findById(id).orElseThrow(NoSuchElementException :: new);
        p.setVotes(p.getVotes() + 1);
        playerRepository.save(p);
        return p;
    }
}
