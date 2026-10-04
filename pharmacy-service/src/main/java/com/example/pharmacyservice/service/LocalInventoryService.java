package com.example.pharmacyservice.service;

import org.springframework.stereotype.Service;

@Service
public class LocalInventoryService {

    public int getLocalStock(Long productId) {
        if (productId == 1) {
            return 20;
        }

        if (productId == 2) {
            return 10;
        }

        return 0;
    }
}