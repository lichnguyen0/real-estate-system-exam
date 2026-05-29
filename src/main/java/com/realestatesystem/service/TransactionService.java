package com.realestatesystem.service;

import com.realestatesystem.model.Transaction;

import java.util.List;

public interface TransactionService {
    List<Transaction> findAll();

    Transaction findById(String id);

    void save(Transaction giaoDich);

    void delete(String id);

    List<Transaction> search(String tenKhachHang, String loaiDichVu);

}
