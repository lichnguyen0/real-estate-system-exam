package com.realestatesystem.validator;

import com.realestatesystem.dto.TransactionDTO;

public class TransactionValidator {

    public static String validate(TransactionDTO dto) {

        if (dto.getMaGiaoDich() == null || dto.getMaGiaoDich().isEmpty()) {
            return "Mã giao dịch bắt buộc nhập";
        }

        if (!dto.getMaGiaoDich().matches("MGD-\\d{4}")) {
            return "Mã phải đúng dạng MGD-XXXX";
        }

        if (dto.getDonGia() <= 500000) {
            return "Đơn giá phải > 500.000";
        }

        if (dto.getDienTich() <= 20) {
            return "Diện tích phải > 20";
        }

        return null;
    }
}
