package com.example.qlnh.repository;

import com.example.qlnh.entity.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien, String> {
    // Kiểm tra trùng SDT khi Thêm mới
    boolean existsBySoDienThoai(String soDienThoai);

    // Kiểm tra trùng SDT khi Cập nhật (bỏ qua chính nhân viên đang sửa)
    boolean existsBySoDienThoaiAndMaNVNot(String soDienThoai, String maNV);
}