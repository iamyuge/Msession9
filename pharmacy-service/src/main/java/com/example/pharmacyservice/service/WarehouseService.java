package com.example.pharmacyservice.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WarehouseService {

    private final RestTemplate restTemplate;
    private final LocalInventoryService localInventoryService;

    public WarehouseService(
            RestTemplate restTemplate,
            LocalInventoryService localInventoryService
    ) {
        this.restTemplate = restTemplate;
        this.localInventoryService = localInventoryService;
    }

    @CircuitBreaker(
            name = "warehouseCB",
            fallbackMethod = "checkWarehouseFallback"
    )
    public String checkWarehouse(Long productId) {
        return restTemplate.getForObject(
                "http://warehouse-service/api/v1/warehouse/stock/" + productId,
                String.class
        );
    }

    public String checkWarehouseFallback(Long productId, Exception e) {
        int localStock = localInventoryService.getLocalStock(productId);

        return "Không thể kết nối kho tổng. Hệ thống sẽ sử dụng dữ liệu tồn kho cục bộ để tiếp tục giao dịch. Tồn kho tại chỗ: "
                + localStock;
    }
}