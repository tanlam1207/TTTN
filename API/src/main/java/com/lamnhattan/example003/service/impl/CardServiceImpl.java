package com.lamnhattan.example003.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.lamnhattan.example003.entity.Card;
import com.lamnhattan.example003.repository.CardRepository;
import com.lamnhattan.example003.service.CardService;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class CardServiceImpl implements CardService {
    private CardRepository CardRepository;

    @Override
    public Card createCard(Card Card) {
        return CardRepository.save(Card);
    }

    @Override
    public Card getCardById(Long CardId) {
        Optional<Card> optionalCard = CardRepository.findById(CardId);
        return optionalCard.get();
    }

    @Override
    public List<Card> getAllCards() {
        return CardRepository.findAll();
    }

    @Override
    public Card updateCard(Card Card) {
        Card existingCard = CardRepository.findById(Card.getCard_id()).get();
        // existingCard.setCustomer(Card.getCustomer());
        existingCard.setGooglecustomer(Card.getGooglecustomer());
        Card updatedCard = CardRepository.save(existingCard);
        return updatedCard;
    }

    @Override
    public void deleteCard(Long CardId) {
        CardRepository.deleteById(CardId);
    }
    @Override
    public Optional<Card> findByGooglecustomer(BigInteger google_customer) {
        return CardRepository.findByGooglecustomer(google_customer);
    }

}
