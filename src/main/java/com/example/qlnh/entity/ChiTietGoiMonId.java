package com.example.qlnh.entity;

import java.io.Serializable;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor
public class ChiTietGoiMonId implements Serializable {
    private Long phieuGoiMon; // Tên biến phải khớp với tên biến ở class dưới
    private Long monAn;
}