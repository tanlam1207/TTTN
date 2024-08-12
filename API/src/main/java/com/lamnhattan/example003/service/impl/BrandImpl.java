package com.lamnhattan.example003.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lamnhattan.example003.entity.Brand;
import com.lamnhattan.example003.repository.BrandRepository;
import com.lamnhattan.example003.service.BrandService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class BrandImpl implements BrandService {
    private BrandRepository Brandepository;

    @Override
    public Brand createBrand(Brand Brand) {
        return Brandepository.save(Brand);
    }

    @Override
    public Brand getBrandById(Long BrandId) {
        Optional<Brand> optionalBrand = Brandepository.findById(BrandId);
        return optionalBrand.get();
    }

    @Override
    public List<Brand> getAllBrands() {
        return Brandepository.findAll();
    }

    @Override
    public Brand updateBrand(Brand Brand) {
        Brand existingBrand = Brandepository.findById(Brand.getId()).get();
        existingBrand.setBrand_name(Brand.getBrand_name());
        existingBrand.setIcon(Brand.getIcon());
        existingBrand.setCreatedAt(Brand.getCreatedAt());
        existingBrand.setUpdatedAt(Brand.getUpdatedAt());
        existingBrand.setCreatedBy(Brand.getCreatedBy());
        existingBrand.setUpdatedBy(Brand.getUpdatedBy());
        existingBrand.setActive(Brand.getActive());
        Brand updatedBrand = Brandepository.save(existingBrand);
        return updatedBrand;
    }

    @Override
    public void deleteBrand(Long BrandId) {
        Brandepository.deleteById(BrandId);
    }

}
