package com.realestatesystem.service.impl;

import com.realestatesystem.model.GiaoDich;
import com.realestatesystem.repository.GiaoDichRepository;
import com.realestatesystem.service.GiaoDichService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GiaoDichServiceImpl implements GiaoDichService {

    private final GiaoDichRepository repository;

    public GiaoDichServiceImpl(GiaoDichRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<GiaoDich> findAll() {
        return repository.findAll();
    }

    @Override
    public GiaoDich findById(String id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void save(GiaoDich giaoDich) {
        repository.save(giaoDich);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    public List<GiaoDich> search(String ten, String loai) {
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
