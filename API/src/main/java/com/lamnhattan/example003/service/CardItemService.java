package com.lamnhattan.example003.service;

import java.util.List;

import com.lamnhattan.example003.entity.Card_item;


public interface CardItemService {
    Card_item createCardItem(Card_item CardItem);

    Card_item getCardItemById(Long CardItemId);

    List<Card_item> getAllCardItems();

    Card_item updateCardItem(Card_item CardItem);

    void deleteCardItem(Long CardItemId);
    List<Card_item> findByCardId(Long cardId);
    void deleteCardItemsByCardId(Long cardId);

}
