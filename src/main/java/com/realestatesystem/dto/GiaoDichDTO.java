package com.realestatesystem.dto;

import lombok.Data;

@Data
public class GiaoDichDTO {
    private String maGiaoDich;
    private String maKhachHang;
    private String loaiDichVu;
    private String ngayGiaoDich;
    private double donGia;
    private double dienTich;

    // getter setter
}
