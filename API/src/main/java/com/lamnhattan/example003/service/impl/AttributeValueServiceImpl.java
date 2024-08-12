package com.lamnhattan.example003.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.lamnhattan.example003.entity.AttributeValue;
import com.lamnhattan.example003.repository.AttributeValueRepository;
import com.lamnhattan.example003.service.AttributeValueService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class AttributeValueServiceImpl implements AttributeValueService {
    private AttributeValueRepository AttributeValueRepository;

    @Override
    public AttributeValue createAttributeValue(AttributeValue AttributeValue) {
        return AttributeValueRepository.save(AttributeValue);
    }

    @Override
    public AttributeValue getAttributeValueById(Long AttributeValueId) {
        Optional<AttributeValue> optionalAttributeValue = AttributeValueRepository.findById(AttributeValueId);
        return optionalAttributeValue.get();
    }

    @Override
    public List<AttributeValue> getAllAttributeValues() {
        return AttributeValueRepository.findAll();
    }

    @Override
    public AttributeValue updateAttributeValue(AttributeValue AttributeValue) {
        AttributeValue existingAttributeValue = AttributeValueRepository.findById(AttributeValue.getId()).get();
        existingAttributeValue.setAttribute(AttributeValue.getAttribute());
        existingAttributeValue.setAttribute_value(AttributeValue.getAttribute_value());
        existingAttributeValue.setColor(AttributeValue.getColor());
        AttributeValue updatedAttributeValue = AttributeValueRepository.save(existingAttributeValue);
        return updatedAttributeValue;
    }

    @Override
    public void deleteAttributeValue(Long AttributeValueId) {
        AttributeValueRepository.deleteById(AttributeValueId);
    }

}
