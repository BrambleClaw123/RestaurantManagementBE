package com.example.qlnh.repository;

import com.example.qlnh.entity.BanAn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BanAnRepository extends JpaRepository<BanAn, String> {
}