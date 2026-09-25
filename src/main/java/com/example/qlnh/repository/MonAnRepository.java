package com.example.qlnh.repository;

import com.example.qlnh.entity.MonAn;
import com.example.qlnh.enums.LoaiMon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

// Chú ý: Đổi kiểu ID sang Long
@Repository
public interface MonAnRepository extends JpaRepository<MonAn, Long> {

    @Query("SELECT m FROM MonAn m WHERE " +
            "(:keyword IS NULL OR LOWER(m.tenMon) LIKE LOWER(CONCAT('%', :keyword, '%'))) AND " +
            "(:loaiMon IS NULL OR m.loaiMon = :loaiMon)")
    List<MonAn> timKiemVaLoc(@Param("keyword") String keyword, @Param("loaiMon") LoaiMon loaiMon);

    // Thống kê dựa trên chuỗi String thực tế từ UI
    @Query("SELECT COUNT(m) FROM MonAn m WHERE m.trangThai = 'Còn phục vụ'")
    long countByTrangThaiConPhucVu();

    @Query("SELECT COUNT(m) FROM MonAn m WHERE m.trangThai = 'Ngưng phục vụ' OR m.trangThai = 'Tạm ngưng'")
    long countByTrangThaiNgungPhucVu();

    @Query("SELECT AVG(m.donGia) FROM MonAn m")
    Double tinhDonGiaTrungBinh();
    // Kiểm tra trùng tên món ăn (không phân biệt hoa/thường)
    boolean existsByTenMonIgnoreCase(String tenMon);
}