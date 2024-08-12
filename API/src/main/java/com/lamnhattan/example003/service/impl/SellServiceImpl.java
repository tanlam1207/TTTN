package com.lamnhattan.example003.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.lamnhattan.example003.entity.Sell;
import com.lamnhattan.example003.repository.SellRepository;
import com.lamnhattan.example003.service.SellService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class SellServiceImpl implements SellService {
    private SellRepository SellRepository;

    @Override
    public Sell createSell(Sell Sell) {
        return SellRepository.save(Sell);
    }

    @Override
    public Sell getSellById(Long SellId) {
        Optional<Sell> optionalSell = SellRepository.findById(SellId);
        return optionalSell.get();
    }

    @Override
    public List<Sell> getAllSells() {
        return SellRepository.findAll();
    }

    @Override
    public Sell updateSell(Sell Sell) {
        Sell existingSell = SellRepository.findById(Sell.getId()).get();
        existingSell.setProduct(Sell.getProduct());
        existingSell.setPrice(Sell.getPrice());
        existingSell.setQuantity(Sell.getQuantity());
        Sell updatedSell = SellRepository.save(existingSell);
        return updatedSell;
    }

    @Override
    public void deleteSell(Long SellId) {
        SellRepository.deleteById(SellId);
    }

}
