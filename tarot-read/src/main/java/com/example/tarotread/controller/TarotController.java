package com.example.tarotread.controller;

import com.example.tarotread.domain.TarotCard;
import com.example.tarotread.repository.TarotCardRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/api/tarot")
public class TarotController {

    private final TarotCardRepository repository;

    public TarotController(TarotCardRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public TarotCard getRandomCard() {
        var cards = repository.findAll();
        return cards.get(new Random().nextInt(cards.size()));
    }
}
