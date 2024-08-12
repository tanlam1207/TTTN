package com.lamnhattan.example003.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.lamnhattan.example003.entity.Attribute;
import com.lamnhattan.example003.repository.AttributeRepository;
import com.lamnhattan.example003.service.AttributeService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class AttributeServiceImpl implements AttributeService {
    private AttributeRepository AttributeRepository;

    @Override
    public Attribute createAttribute(Attribute Attribute) {
        return AttributeRepository.save(Attribute);
    }

    @Override
    public Attribute getAttributeById(Long AttributeId) {
        Optional<Attribute> optionalAttribute = AttributeRepository.findById(AttributeId);
        return optionalAttribute.get();
    }

    @Override
    public List<Attribute> getAllAttributes() {
        return AttributeRepository.findAll();
    }

    @Override
    public Attribute updateAttribute(Attribute Attribute) {
        Attribute existingAttribute = AttributeRepository.findById(Attribute.getId()).get();
        existingAttribute.setAttribute_name(Attribute.getAttribute_name());
        existingAttribute.setCreated_at(Attribute.getCreated_at());
        existingAttribute.setUpdated_at(Attribute.getUpdated_at());
        existingAttribute.setCreatedBy(Attribute.getCreatedBy());
        existingAttribute.setUpdatedBy(Attribute.getUpdatedBy());
        Attribute updatedAttribute = AttributeRepository.save(existingAttribute);
        return updatedAttribute;
    }

    @Override
    public void deleteAttribute(Long AttributeId) {
        AttributeRepository.deleteById(AttributeId);
    }

}
