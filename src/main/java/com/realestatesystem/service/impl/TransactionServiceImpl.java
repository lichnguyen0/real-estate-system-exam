package com.realestatesystem.service.impl;

import com.realestatesystem.model.Transaction;
import com.realestatesystem.repository.TransactionRepository;
import com.realestatesystem.service.TransactionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository repository;

    public TransactionServiceImpl(TransactionRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Transaction> findAll() {
        return repository.findAll();
    }

    @Override
    public Transaction findById(String id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void save(Transaction giaoDich) {
        repository.save(giaoDich);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }


    //Phương thức này có nhiệm vụ chính là tìm kiếm danh sách giao dịch (GiaoDich) theo điều kiện linh hoạt do người dùng nhập vào.
    @Override
    public List<Transaction> search(String ten, String loai) {
        boolean hasTen = ten != null && !ten.trim().isEmpty();
        boolean hasLoai = loai != null && !loai.trim().isEmpty();

        if (hasTen && hasLoai) {
            return repository.findByKhachHang_TenKhachHangContainingIgnoreCaseAndLoaiDichVuIgnoreCase(ten.trim(), loai.trim());
        } else if (hasTen) {
            return repository.findByKhachHang_TenKhachHangContainingIgnoreCase(ten.trim());
        } else if (hasLoai) {
            return repository.findByLoaiDichVuIgnoreCase(loai.trim());
        } else {
            return repository.findAll();
        }
    }
}
