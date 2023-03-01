package dev.fastfoodapi.repository;

import dev.fastfoodapi.model.LoaiMatHang;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoaiMatHangRepo extends JpaRepository<LoaiMatHang, Long> {
}
