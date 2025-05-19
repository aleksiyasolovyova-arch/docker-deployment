package com.example.tarotread.repository;

import com.example.tarotread.domain.TarotCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarotCardRepository extends JpaRepository<TarotCard, Long> {
}
