package com.lamnhattan.example003.service;

import java.util.List;

import com.lamnhattan.example003.entity.Variant_value;


public interface VarlantValueService {
    Variant_value createVarlantValue(Variant_value VarlantValue);

    Variant_value getVarlantValueById(Long VarlantValueId);

    List<Variant_value> getAllVarlantValues();

    // Variant_value updateVarlantValue(Variant_value VarlantValue);

    void deleteVarlantValue(Long VarlantValueId);
}
