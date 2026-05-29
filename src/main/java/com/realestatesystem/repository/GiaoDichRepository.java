package com.realestatesystem.repository;

import com.realestatesystem.model.GiaoDich;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GiaoDichRepository extends JpaRepository<GiaoDich, String> {

    List<GiaoDich> findByKhachHang_TenKhachHangContainingIgnoreCase(String ten);

    List<GiaoDich> findByLoaiDichVuIgnoreCase(String loai);

    List<GiaoDich> findByKhachHang_TenKhachHangContainingIgnoreCaseAndLoaiDichVuIgnoreCase(String ten, String loai);
}
