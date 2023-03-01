package dev.fastfoodapi.service;

import dev.fastfoodapi.model.PhieuGiamGia;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PhieuGiamGiaService {

    //Hàm CRUD mặc định
    PhieuGiamGia save(PhieuGiamGia obj);

    Optional<PhieuGiamGia> findById(String id);

    List<PhieuGiamGia> findAll();

    PhieuGiamGia update(String id, PhieuGiamGia obj);

    void delete(String id);

    //Các hàm khác
    List<PhieuGiamGia> findAllByKhachHang(UUID id);

}
