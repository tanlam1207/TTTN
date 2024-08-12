package com.lamnhattan.example003.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.lamnhattan.example003.entity.Gallery;
import com.lamnhattan.example003.entity.Shiping;
import com.lamnhattan.example003.repository.ShippingRepository;
import com.lamnhattan.example003.service.ShippingService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class ShippingServiceImpl implements ShippingService {
    private ShippingRepository ShippingRepository;

    @Override
    public Shiping createShipping(Shiping Shipping) {
        return ShippingRepository.save(Shipping);
    }

    @Override
    public Shiping getShippingById(Long ShippingId) {
        Optional<Shiping> optionalShipping = ShippingRepository.findById(ShippingId);
        return optionalShipping.get();
    }

    @Override
    public List<Shiping> getAllShippings() {
        return ShippingRepository.findAll();
    }

    @Override
    public Shiping updateShipping(Shiping Shipping) {
        Shiping existingShipping = ShippingRepository.findById(Shipping.getId()).get();
        existingShipping.setName(Shipping.getName());
        existingShipping.setActive(Shipping.getActive());
        existingShipping.setIcon_path(Shipping.getIcon_path());
        existingShipping.setCreated_at(Shipping.getCreated_at());
        existingShipping.setUpdated_at(Shipping.getUpdated_at());
        existingShipping.setCreatedBy(Shipping.getCreatedBy());
        existingShipping.setUpdatedBy(Shipping.getUpdatedBy());

        Shiping updatedShipping = ShippingRepository.save(existingShipping);
        return updatedShipping;
    }

    @Override
    public void deleteShipping(Long ShippingId) {
        ShippingRepository.deleteById(ShippingId);
    }

}
