package com.example.qlnh.service.impl;

import com.example.qlnh.dto.request.PhieuDatBanRequest;
import com.example.qlnh.dto.response.PhieuDatBanResponse;
import com.example.qlnh.entity.BanAn;
import com.example.qlnh.entity.KhachHang;
import com.example.qlnh.entity.PhieuDatBan;
import com.example.qlnh.repository.BanAnRepository;
import com.example.qlnh.repository.KhachHangRepository;
import com.example.qlnh.repository.PhieuDatBanRepository;
import com.example.qlnh.service.PhieuDatBanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhieuDatBanServiceImpl implements PhieuDatBanService {

    @Autowired
    private PhieuDatBanRepository phieuDatBanRepository;

    @Autowired
    private KhachHangRepository khachHangRepository;

    @Autowired
    private BanAnRepository banAnRepository;

    // 1. Hàm map Entity sang DTO để trả về cho Frontend
    private PhieuDatBanResponse toResponse(PhieuDatBan phieu) {
        PhieuDatBanResponse res = new PhieuDatBanResponse();
        res.setMaPhieuDB(phieu.getMaPhieuDB());
        res.setMaPhieuHienThi("#RES-" + phieu.getMaPhieuDB());
        res.setTenKhachHang(phieu.getKhachHang().getHoTen());
        res.setSoDienThoai(phieu.getKhachHang().getSoDienThoai());

        // Bóc tách ngày và giờ từ LocalDateTime của Entity ra riêng
        res.setNgayDat(phieu.getNgayGioDat().toLocalDate());
        res.setGioDat(phieu.getNgayGioDat().toLocalTime());

        res.setMaBan(phieu.getBanAn().getMaBan());
        res.setSoNguoiLon(phieu.getSoNguoiLon());
        res.setSoTreEm(phieu.getSoTreEm());
        res.setYeuCau(phieu.getYeuCau());
        return res;
    }

    // 2. Logic thông minh: Tìm khách cũ, nếu không có thì tạo mới, nếu có thì cập nhật tên mới nhất
    private KhachHang xuLyKhachHang(String hoTen, String soDienThoai) {
        KhachHang khachHang = khachHangRepository.findBySoDienThoai(soDienThoai)
                .orElse(new KhachHang());

        khachHang.setSoDienThoai(soDienThoai);
        khachHang.setHoTen(hoTen);

        return khachHangRepository.save(khachHang);
    }

    @Override
    public List< PhieuDatBanResponse > layDanhSach() {
        return phieuDatBanRepository.findAllByOrderByNgayGioDatAsc().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PhieuDatBanResponse layChiTiet(Long id) {
        PhieuDatBan phieu = phieuDatBanRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy phiếu đặt bàn mã #" + id));
        return toResponse(phieu);
    }

    @Override
    public PhieuDatBanResponse taoPhieuDatBan(PhieuDatBanRequest request) {
        KhachHang kh = xuLyKhachHang(request.getTenKhachHang().trim(), request.getSoDienThoai().trim());

        BanAn ban = banAnRepository.findById(request.getMaBan())
                .orElseThrow(() -> new IllegalArgumentException("Bàn " + request.getMaBan() + " không tồn tại"));

        PhieuDatBan phieu = new PhieuDatBan();
        phieu.setKhachHang(kh);
        phieu.setBanAn(ban);
        // Nhận ngày riêng, giờ riêng từ FE và ghép lại thành LocalDateTime để lưu DB
        phieu.setNgayGioDat(LocalDateTime.of(request.getNgayDat(), request.getGioDat()));
        phieu.setSoNguoiLon(request.getSoNguoiLon());
        phieu.setSoTreEm(request.getSoTreEm());
        phieu.setYeuCau(request.getYeuCau());

        return toResponse(phieuDatBanRepository.save(phieu));
    }

    @Override
    public PhieuDatBanResponse capNhatPhieu(Long id, PhieuDatBanRequest request) {
        PhieuDatBan phieu = phieuDatBanRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy phiếu đặt bàn mã #" + id));

        KhachHang kh = xuLyKhachHang(request.getTenKhachHang().trim(), request.getSoDienThoai().trim());

        BanAn ban = banAnRepository.findById(request.getMaBan())
                .orElseThrow(() -> new IllegalArgumentException("Bàn " + request.getMaBan() + " không tồn tại"));

        phieu.setKhachHang(kh);
        phieu.setBanAn(ban);
        // Ghép lại để lưu
        phieu.setNgayGioDat(LocalDateTime.of(request.getNgayDat(), request.getGioDat()));
        phieu.setSoNguoiLon(request.getSoNguoiLon());
        phieu.setSoTreEm(request.getSoTreEm());
        phieu.setYeuCau(request.getYeuCau());

        return toResponse(phieuDatBanRepository.save(phieu));
    }

    @Override
    public void xoaPhieu(Long id) {
        if (!phieuDatBanRepository.existsById(id)) {
            throw new IllegalArgumentException("Không tìm thấy phiếu đặt bàn mã #" + id + " để xóa");
        }
        phieuDatBanRepository.deleteById(id);
    }
}