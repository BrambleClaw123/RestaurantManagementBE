package com.example.qlnh.service;

import com.example.qlnh.dto.request.TaiKhoanRequest;
import com.example.qlnh.dto.response.TaiKhoanResponse;
import com.example.qlnh.entity.TaiKhoan;
import java.util.List;

public interface TaiKhoanService {

    // Cập nhật: Trả về TaiKhoanResponse và nhận keyword
    List<TaiKhoanResponse> layDanhSachTaiKhoan(String keyword);

    TaiKhoan themTaiKhoan(TaiKhoanRequest request);

    // Hàm cho nút "Sửa"
    TaiKhoanResponse capNhatTaiKhoan(String maNV, TaiKhoanRequest request);

    // Hàm cho nút "Khóa" / "Mở khóa" ngoài bảng
    TaiKhoanResponse thayDoiTrangThai(String maNV);
}