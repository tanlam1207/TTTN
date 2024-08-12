package com.lamnhattan.example003.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.lamnhattan.example003.entity.Variant_value;
import com.lamnhattan.example003.repository.VarlantValueRepository;
import com.lamnhattan.example003.service.VarlantValueService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class VarlantValueServiceImpl implements VarlantValueService {
    private VarlantValueRepository VarlantValueRepository;

    @Override
    public Variant_value createVarlantValue(Variant_value VarlantValue) {
        return VarlantValueRepository.save(VarlantValue);
    }

    @Override
    public Variant_value getVarlantValueById(Long VarlantValueId) {
        Optional<Variant_value> optionalVarlantValue = VarlantValueRepository.findById(VarlantValueId);
        return optionalVarlantValue.get();
    }

    @Override
    public List<Variant_value> getAllVarlantValues() {
        return VarlantValueRepository.findAll();
    }

    // @Override
    // public Variant_value updateVarlantValue(Variant_value VarlantValue) {
    //     Variant_value existingVarlantValue = VarlantValueRepository.findById(VarlantValue.getId()).get();
    //     existingVarlantValue.setVarlant(VarlantValue.getVarlant());
    //     existingVarlantValue.setPrice(VarlantValue.getPrice());
    //     existingVarlantValue.setQuantity(VarlantValue.getQuantity());
    //     Variant_value updatedVarlantValue = VarlantValueRepository.save(existingVarlantValue);
    //     return updatedVarlantValue;
    // }

    @Override
    public void deleteVarlantValue(Long VarlantValueId) {
        VarlantValueRepository.deleteById(VarlantValueId);
    }

}
