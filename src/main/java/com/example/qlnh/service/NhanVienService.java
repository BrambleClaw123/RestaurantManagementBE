package com.example.qlnh.service;

import com.example.qlnh.dto.request.NhanVienRequest;
import com.example.qlnh.dto.response.NhanVienResponse;

public interface NhanVienService {
    NhanVienResponse themNhanVien(NhanVienRequest request);
    NhanVienResponse capNhatNhanVien(String maNV, NhanVienRequest request);
}