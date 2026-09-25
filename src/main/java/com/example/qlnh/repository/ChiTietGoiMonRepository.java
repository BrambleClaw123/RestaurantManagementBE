package com.example.qlnh.repository;

import com.example.qlnh.dto.response.baocao.BaoCaoBanChayResponse;
import com.example.qlnh.entity.ChiTietGoiMon;
import com.example.qlnh.entity.ChiTietGoiMonId;
import com.example.qlnh.entity.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface ChiTietGoiMonRepository extends JpaRepository<ChiTietGoiMon, ChiTietGoiMonId> {
    // Thống kê món ăn bán chạy
    @Query("SELECT new com.example.qlnh.dto.response.baocao.BaoCaoBanChayResponse$ChiTiet(" +
            "m.tenMon, CAST(m.loaiMon AS string), SUM(c.soLuong), SUM(c.soLuong * c.donGia)) " +
            "FROM ChiTietGoiMon c JOIN c.monAn m JOIN c.phieuGoiMon p " +
            "WHERE p.thoiGianLap BETWEEN :tuNgay AND :denNgay " +
            "GROUP BY m.maMon, m.tenMon, m.loaiMon " +
            "ORDER BY SUM(c.soLuong) DESC")
    List<BaoCaoBanChayResponse.ChiTiet> layChiTietBanChay(@Param("tuNgay") LocalDateTime tuNgay, @Param("denNgay") LocalDateTime denNgay);
}
