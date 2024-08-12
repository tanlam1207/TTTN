package com.lamnhattan.example003.service;

import java.util.List;

import com.lamnhattan.example003.entity.AttributeValue;


public interface AttributeValueService {
    AttributeValue createAttributeValue(AttributeValue AttributeValue);

    AttributeValue getAttributeValueById(Long AttributeValueId);

    List<AttributeValue> getAllAttributeValues();

    AttributeValue updateAttributeValue(AttributeValue AttributeValue);

    void deleteAttributeValue(Long AttributeValueId);
}
