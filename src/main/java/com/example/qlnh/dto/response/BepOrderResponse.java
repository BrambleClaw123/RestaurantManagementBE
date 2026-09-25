package com.example.qlnh.dto.response;

import lombok.Data;
import java.util.List;

@Data
public class BepOrderResponse {
    private String maBan;
    private Long maPhieuGM;
    private String maPhieuHienThi;
    private String thoiGian;
    private String trangThai;
    private String viTriDoi;

    // Thêm trường ghi chú cho toàn bộ Order
    private String ghiChu;

    private List< BepMonResponse > danhSachMon;
}