package com.example.qlnh.repository;

import com.example.qlnh.entity.MonAn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonAnRepository extends JpaRepository<MonAn, Long> {
    // Long tương ứng với MaMon (Khóa chính)
}