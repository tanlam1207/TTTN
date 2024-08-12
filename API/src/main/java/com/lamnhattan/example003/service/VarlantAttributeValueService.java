package com.lamnhattan.example003.service;

import java.util.List;

import com.lamnhattan.example003.entity.Variant_attribute_value;


public interface VarlantAttributeValueService {
    Variant_attribute_value createVarlantAttributeValue(Variant_attribute_value VarlantAttributeValue);

    Variant_attribute_value getVarlantAttributeValueById(Long VarlantAttributeValueId);

    List<Variant_attribute_value> getAllVarlantAttributeValues();

    Variant_attribute_value updateVarlantAttributeValue(Variant_attribute_value VarlantAttributeValue);

    void deleteVarlantAttributeValue(Long VarlantAttributeValueId);
}
