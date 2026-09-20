package com.example.qlnh.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "chitietgoimon")
@IdClass(ChiTietGoiMonId.class) // Khai báo dùng khóa kép
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ChiTietGoiMon {

    @Id
    @ManyToOne @JoinColumn(name = "maPhieuGM")
    private PhieuGoiMon phieuGoiMon;

    @Id
    @ManyToOne @JoinColumn(name = "maMon")
    private MonAn monAn;

    private Integer soLuong;
    private Double donGia;

    @Column(length = 50)
    private String trangThaiBep = "Chờ chế biến";
}