package com.lamnhattan.example003.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import com.lamnhattan.example003.entity.Card;


public interface CardService {
    Card createCard(Card Card);

    Card getCardById(Long CardId);

    List<Card> getAllCards();

    Card updateCard(Card Card);

    void deleteCard(Long CardId);
    Optional<Card> findByGooglecustomer(BigInteger google_customer);
}
