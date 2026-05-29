package com.realestatesystem.service;

import com.realestatesystem.model.GiaoDich;

import java.util.List;

public interface GiaoDichService {
    List<GiaoDich> findAll();

    GiaoDich findById(String id);

    void save(GiaoDich giaoDich);

    void delete(String id);

    List<GiaoDich> search(String tenKhachHang, String loaiDichVu);

}
