package com.example.tarotread.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
public class TarotCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;
    @Getter
    @Column
    private String name;
    @Getter
    @Column
    private String meaning;

    public TarotCard(Long id, String name, String meaning) {
        this.id = id;
        this.name = name;
        this.meaning = meaning;
    }
}
