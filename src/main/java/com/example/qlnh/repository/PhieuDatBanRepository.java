package com.example.qlnh.repository;

import com.example.qlnh.entity.PhieuDatBan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PhieuDatBanRepository extends JpaRepository<PhieuDatBan, Long> {
    // Sắp xếp các phiếu đặt bàn mới nhất hoặc sắp tới lên đầu
    List<PhieuDatBan> findAllByOrderByNgayGioDatAsc();
    Optional< PhieuDatBan > findFirstByBanAn_MaBanOrderByNgayGioDatDesc(String maBan);
}