package dev.fastfoodapi.service;

import dev.fastfoodapi.model.DonDatHang;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DonDatHangService {

    //Hàm CRUD mặc định
    DonDatHang save(DonDatHang obj);

    Optional<DonDatHang> findById(Long id);

    List<DonDatHang> findAll();

    DonDatHang update(Long id, DonDatHang obj);

    void delete(Long id);

    //Một số hàm khác
    List<DonDatHang> findAllByKhachHang(UUID userId);

    List<DonDatHang> findAllByTrangThai(String trangThai);

    List<DonDatHang> ngayTheoThang();

    List<DonDatHang> thangTrongNam();

    List<DonDatHang> bayNgayGanDay();
}
