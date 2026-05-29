package com.realestatesystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "khach_hang")
@Data
@NoArgsConstructor
public class KhachHang {
    @Id
    private String maKhachHang;

    private String tenKhachHang;

    private String soDienThoai;

    private String email;


}
