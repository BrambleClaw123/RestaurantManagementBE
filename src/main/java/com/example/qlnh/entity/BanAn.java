package com.example.qlnh.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "banan")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class BanAn {
    @Id
    @Column(length = 20)
    private String maBan;

    @Column(length = 50)
    private String trangThai = "Trống";
}