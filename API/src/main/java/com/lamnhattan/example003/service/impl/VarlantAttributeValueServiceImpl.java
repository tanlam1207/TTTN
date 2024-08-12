package com.lamnhattan.example003.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.lamnhattan.example003.entity.Variant_attribute_value;
import com.lamnhattan.example003.repository.VarlantAttributeValueRepository;
import com.lamnhattan.example003.service.VarlantAttributeValueService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class VarlantAttributeValueServiceImpl implements VarlantAttributeValueService {
    private VarlantAttributeValueRepository VarlantAttributeValueRepository;

    @Override
    public Variant_attribute_value createVarlantAttributeValue(Variant_attribute_value VarlantAttributeValue) {
        return VarlantAttributeValueRepository.save(VarlantAttributeValue);
    }

    @Override
    public Variant_attribute_value getVarlantAttributeValueById(Long VarlantAttributeValueId) {
        Optional<Variant_attribute_value> optionalVarlantAttributeValue = VarlantAttributeValueRepository.findById(VarlantAttributeValueId);
        return optionalVarlantAttributeValue.get();
    }

    @Override
    public List<Variant_attribute_value> getAllVarlantAttributeValues() {
        return VarlantAttributeValueRepository.findAll();
    }

    @Override
    public Variant_attribute_value updateVarlantAttributeValue(Variant_attribute_value VarlantAttributeValue) {
        Variant_attribute_value existingVarlantAttributeValue = VarlantAttributeValueRepository.findById(VarlantAttributeValue.getVariant_attribute_value_id()).get();
        existingVarlantAttributeValue.setAttributeValue(VarlantAttributeValue.getAttributeValue());

        Variant_attribute_value updatedVarlantAttributeValue = VarlantAttributeValueRepository.save(existingVarlantAttributeValue);
        return updatedVarlantAttributeValue;
    }

    @Override
    public void deleteVarlantAttributeValue(Long VarlantAttributeValueId) {
        VarlantAttributeValueRepository.deleteById(VarlantAttributeValueId);
    }

}
