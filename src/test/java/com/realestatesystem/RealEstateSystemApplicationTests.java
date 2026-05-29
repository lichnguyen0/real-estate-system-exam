package com.realestatesystem;

import com.realestatesystem.model.Transaction;
import com.realestatesystem.repository.TransactionRepository;
import com.realestatesystem.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class RealEstateSystemApplicationTests {

    @Autowired
    private TransactionService service;

    @Autowired
    private TransactionRepository repository;

    @Test
    void testCombinedSearch() {
        System.out.println("============= JUNIT COMBINED SEARCH TEST =============");
        
        // 1. Search for customer "Nguyễn" and type "Đất" (should return MGD-0002)
        List<Transaction> results = service.search("Nguyễn", "Đất");
        System.out.println("[TEST] 'Nguyễn' AND 'Đất' -> Size: " + results.size());
        for (Transaction gd : results) {
            System.out.println(" -> " + gd.getMaGiaoDich() + " | Customer: " + gd.getKhachHang().getTenKhachHang() + " | Service: " + gd.getLoaiDichVu());
        }

        // 2. Search for customer "Nguyễn" and type "Nhà đất" (should return MGD-0001, MGD-0003)
        List<Transaction> results2 = service.search("Nguyễn", "Nhà đất");
        System.out.println("[TEST] 'Nguyễn' AND 'Nhà đất' -> Size: " + results2.size());
        for (Transaction gd : results2) {
            System.out.println(" -> " + gd.getMaGiaoDich() + " | Customer: " + gd.getKhachHang().getTenKhachHang() + " | Service: " + gd.getLoaiDichVu());
        }
        
        System.out.println("============= JUNIT COMBINED SEARCH END =============");
    }
}
