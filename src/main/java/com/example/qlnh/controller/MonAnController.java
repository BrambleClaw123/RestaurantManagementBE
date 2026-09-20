package com.example.qlnh.controller;

import com.example.qlnh.entity.MonAn;
import com.example.qlnh.repository.MonAnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mon-an")
@CrossOrigin(origins = "*")
public class MonAnController {

    @Autowired
    private MonAnRepository monAnRepository;

    // 1. API Lấy danh sách toàn bộ món ăn (GET)
    @GetMapping
    public List<MonAn> layDanhSachMonAn() {
        return monAnRepository.findAll();
    }

    // 2. API Thêm một món ăn mới (POST)
    @PostMapping
    public MonAn themMonAn(@RequestBody MonAn monAnMoi) {
        // Tự động lưu vào MySQL và trả về cục data vừa lưu
        return monAnRepository.save(monAnMoi);
    }
}