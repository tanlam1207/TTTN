package com.lamnhattan.example003.service;

import java.util.List;

import com.lamnhattan.example003.entity.Attribute;


public interface AttributeService {
    Attribute createAttribute(Attribute Attribute);

    Attribute getAttributeById(Long AttributeId);

    List<Attribute> getAllAttributes();

    Attribute updateAttribute(Attribute Attribute);

    void deleteAttribute(Long AttributeId);
}
