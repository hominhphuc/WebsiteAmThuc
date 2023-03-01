package dev.fastfoodapi.repository;

import dev.fastfoodapi.model.PhieuGiamGia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface PhieuGiamGiaRepo extends JpaRepository<PhieuGiamGia, String> {

    @Query("SELECT o FROM PhieuGiamGia o WHERE o.khachHang.userId = :id AND o.trangThai = 'Chưa sử dụng'")
    List<PhieuGiamGia> findAllByKhachHang(UUID id);

}
