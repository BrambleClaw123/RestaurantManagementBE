package com.example.qlnh.converter;

import com.example.qlnh.dto.response.NhanVienResponse;
import com.example.qlnh.entity.NhanVien;
import org.springframework.stereotype.Component;

@Component
public class NhanVienConverter {

    public NhanVienResponse toResponse(NhanVien nv) {
        if (nv == null) return null;

        NhanVienResponse response = new NhanVienResponse();
        response.setMaNV(nv.getMaNV());
        response.setHoTen(nv.getHoTen());
        response.setVaiTro(nv.getVaiTro());
        response.setSoDienThoai(nv.getSoDienThoai());
        return response;
    }
}