package com.realestatesystem.repository;

import com.realestatesystem.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, String> {

    List<Transaction> findByKhachHang_TenKhachHangContainingIgnoreCase(String ten);

    List<Transaction> findByLoaiDichVuIgnoreCase(String loai);

    List<Transaction> findByKhachHang_TenKhachHangContainingIgnoreCaseAndLoaiDichVuIgnoreCase(String ten, String loai);
}
