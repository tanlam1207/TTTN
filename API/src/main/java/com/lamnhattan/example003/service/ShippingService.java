package com.lamnhattan.example003.service;

import java.util.List;

import com.lamnhattan.example003.entity.Shiping;


public interface ShippingService {
    Shiping createShipping(Shiping Shipping);

    Shiping getShippingById(Long ShippingId);

    List<Shiping> getAllShippings();

    Shiping updateShipping(Shiping Shipping);

    void deleteShipping(Long ShippingId);
}
