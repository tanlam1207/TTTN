package com.lamnhattan.example003.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lamnhattan.example003.entity.Card_item;


public interface CardItemRepository extends JpaRepository<Card_item, Long>{
    List<Card_item> findByCard(Long cardId);
    void deleteByCard(Long cardId);
}
