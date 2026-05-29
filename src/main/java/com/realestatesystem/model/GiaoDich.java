package com.realestatesystem.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "giao_dich")
@Data
@NoArgsConstructor
public class GiaoDich {

    @Id
    private String maGiaoDich;

    @ManyToOne
    @JoinColumn(name = "ma_khach_hang")
    private KhachHang khachHang;

    private String loaiDichVu;

    private LocalDate ngayGiaoDich;

    private double donGia;

    private double dienTich;;
}
