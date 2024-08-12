package com.lamnhattan.example003.service;

import java.util.List;

import com.lamnhattan.example003.entity.Sell;


public interface SellService {
    Sell createSell(Sell Sell);

    Sell getSellById(Long SellId);

    List<Sell> getAllSells();

    Sell updateSell(Sell Sell);

    void deleteSell(Long SellId);
}
