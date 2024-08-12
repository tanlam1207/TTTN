    package com.lamnhattan.example003.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.lamnhattan.example003.entity.Card_item;
import com.lamnhattan.example003.repository.CardItemRepository;
import com.lamnhattan.example003.service.CardItemService;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class CardItemServiceImpl implements CardItemService {
    private CardItemRepository CardItemRepository;

    @Override
    public Card_item createCardItem(Card_item CardItem) {
        return CardItemRepository.save(CardItem);
    }

    @Override
    public Card_item getCardItemById(Long CardItemId) {
        Optional<Card_item> optionalCardItem = CardItemRepository.findById(CardItemId);
        return optionalCardItem.get();
    }

    @Override
    public List<Card_item> getAllCardItems() {
        return CardItemRepository.findAll();
    }

    @Override
    public Card_item updateCardItem(Card_item CardItem) {
        Card_item existingCardItem = CardItemRepository.findById(CardItem.getId()).get();
        existingCardItem.setCard(CardItem.getCard());
        existingCardItem.setProduct_id(CardItem.getProduct_id());
        existingCardItem.setQuantity(CardItem.getQuantity());
        existingCardItem.setRegular_price(CardItem.getRegular_price());
        Card_item updatedCardItem = CardItemRepository.save(existingCardItem);
        return updatedCardItem;
    }

    @Override
    public void deleteCardItem(Long CardItemId) {
        CardItemRepository.deleteById(CardItemId);
    }
    @Override
    public List<Card_item> findByCardId(Long cardId) {
        return CardItemRepository.findByCard(cardId);
    }
    @Override
    @Transactional
    public void deleteCardItemsByCardId(Long cardId) {
        CardItemRepository.deleteByCard(cardId);
    }
}
