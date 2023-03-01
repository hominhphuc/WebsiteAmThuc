package dev.fastfoodapi.service;

import dev.fastfoodapi.model.ChiTietDonDatHang;

import java.util.List;
import java.util.Optional;

public interface ChiTietDonDatHangService {

    //Hàm CRUD mặc định==========================================
    ChiTietDonDatHang save(ChiTietDonDatHang obj);

    Optional<ChiTietDonDatHang> findById(Long id);

    List<ChiTietDonDatHang> findAll();

    ChiTietDonDatHang update(Long id, ChiTietDonDatHang obj);

    void delete(Long id);

    //Một số hàm khác ===============================================
    List<ChiTietDonDatHang> findAllByDonDatHang(Long id);

}
