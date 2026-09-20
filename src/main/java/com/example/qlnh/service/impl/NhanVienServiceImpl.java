package com.example.qlnh.service.impl;

import com.example.qlnh.converter.NhanVienConverter;
import com.example.qlnh.dto.request.NhanVienRequest;
import com.example.qlnh.dto.response.NhanVienResponse;
import com.example.qlnh.entity.NhanVien;
import com.example.qlnh.repository.NhanVienRepository;
import com.example.qlnh.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NhanVienServiceImpl implements NhanVienService {

    @Autowired
    private NhanVienRepository nhanVienRepository;

    @Autowired
    private NhanVienConverter nhanVienConverter;

    @Override
    public NhanVienResponse themNhanVien(NhanVienRequest request) {
        // Chỉ cần kiểm tra Số điện thoại
        if (nhanVienRepository.existsBySoDienThoai(request.getSoDienThoai())) {
            throw new IllegalArgumentException("Số điện thoại này đã được đăng ký cho nhân viên khác.");
        }

        NhanVien nv = new NhanVien();
        nv.setMaNV("NV" + System.currentTimeMillis());
        nv.setHoTen(request.getHoTen());
        nv.setVaiTro(request.getVaiTro()); // Đã là kiểu Enum VaiTro
        nv.setSoDienThoai(request.getSoDienThoai());

        return nhanVienConverter.toResponse(nhanVienRepository.save(nv));
    }

    @Override
    public NhanVienResponse capNhatNhanVien(String maNV, NhanVienRequest request) {
        NhanVien nvHienTai = nhanVienRepository.findById(maNV)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy nhân viên."));

        if (nhanVienRepository.existsBySoDienThoaiAndMaNVNot(request.getSoDienThoai(), maNV)) {
            throw new IllegalArgumentException("Số điện thoại này đã được đăng ký cho nhân viên khác.");
        }

        nvHienTai.setHoTen(request.getHoTen());
        nvHienTai.setVaiTro(request.getVaiTro());
        nvHienTai.setSoDienThoai(request.getSoDienThoai());

        return nhanVienConverter.toResponse(nhanVienRepository.save(nvHienTai));
    }
}