package com.lamnhattan.example003.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.lamnhattan.example003.entity.Variant;
import com.lamnhattan.example003.repository.VarlantRepository;
import com.lamnhattan.example003.service.VarlantService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor

public class VarlantServiceImpl implements VarlantService {
    private VarlantRepository VarlantRepository;

    @Override
    public Variant createVarlant(Variant Varlant) {
        return VarlantRepository.save(Varlant);
    }

    @Override
    public Variant getVarlantById(UUID VarlantId) {
        Optional<Variant> optionalVarlant = VarlantRepository.findById(VarlantId);
        return optionalVarlant.get();
    }

    @Override
    public List<Variant> getAllVarlants() {
        return VarlantRepository.findAll();
    }

    // @Override
    // public Variant updateVarlant(Variant Varlant) {
    //     Variant existingVarlant = VarlantRepository.findById(Varlant.getId()).get();
    //     existingVarlant.setVarlantAttributeValue(Varlant.getVarlantAttributeValue());
    //     existingVarlant.setProduct(Varlant.getProduct());
    //     Variant updatedVarlant = VarlantRepository.save(existingVarlant);
    //     return updatedVarlant;
    // }

    @Override
    public void deleteVarlant(UUID VarlantId) {
        VarlantRepository.deleteById(VarlantId);
    }

}
