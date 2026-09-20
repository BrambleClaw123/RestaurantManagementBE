package com.example.qlnh.controller;

import com.example.qlnh.dto.request.NhanVienRequest;
import com.example.qlnh.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/nhan-vien")
@CrossOrigin(origins = "*")
public class NhanVienController {

    @Autowired
    private NhanVienService nhanVienService;

    // API Thêm mới nhân viên
    @PostMapping
    public ResponseEntity<?> themNhanVien(@RequestBody NhanVienRequest request) {
        try {
            return ResponseEntity.ok(nhanVienService.themNhanVien(request));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Lỗi hệ thống: " + e.getMessage());
        }
    }

    // API Chỉnh sửa nhân viên
    @PutMapping("/{maNV}")
    public ResponseEntity<?> capNhatNhanVien(
            @PathVariable String maNV,
            @RequestBody NhanVienRequest request) {
        try {
            return ResponseEntity.ok(nhanVienService.capNhatNhanVien(maNV, request));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Lỗi hệ thống: " + e.getMessage());
        }
    }
}