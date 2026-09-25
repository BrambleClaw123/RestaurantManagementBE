package com.example.qlnh.dto.response;

import lombok.Data;

@Data
public class ThucDonPhucVuResponse {
    private Long maMon;
    private String tenMon;
    private String loaiMon; // VD: "Món chính", "Đồ uống" (để FE làm tab lọc)
    private Double donGia;
}