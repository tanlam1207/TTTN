package com.lamnhattan.example003.service;

import java.util.List;

import com.lamnhattan.example003.entity.Brand;

public interface BrandService {

    Brand createBrand(Brand Brand);

    Brand getBrandById(Long BrandId);

    List<Brand> getAllBrands();

    Brand updateBrand(Brand Brand);

    void deleteBrand(Long BrandId);
}
