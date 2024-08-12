package com.lamnhattan.example003.service;

import java.util.List;
import java.util.UUID;

import com.lamnhattan.example003.entity.Variant;


public interface VarlantService {
    Variant createVarlant(Variant Varlant);

    Variant getVarlantById(UUID VarlantId);

    List<Variant> getAllVarlants();

    // Variant updateVarlant(Variant Varlant);

    void deleteVarlant(UUID VarlantId);
}
