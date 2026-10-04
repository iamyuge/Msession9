package com.example.pharmacyservice.controller;

import com.example.pharmacyservice.service.WarehouseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/warehouse")
public class WarehouseController {

    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @GetMapping("/check/{productId}")
    public ResponseEntity<String> checkWarehouse(
            @PathVariable Long productId
    ) {
        return ResponseEntity.ok(
                warehouseService.checkWarehouse(productId)
        );
    }
}