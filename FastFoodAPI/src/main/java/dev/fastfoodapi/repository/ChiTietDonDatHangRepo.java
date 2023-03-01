package dev.fastfoodapi.repository;


import dev.fastfoodapi.model.ChiTietDonDatHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChiTietDonDatHangRepo extends JpaRepository<ChiTietDonDatHang, Long> {

    @Query("SELECT ctddh FROM ChiTietDonDatHang ctddh WHERE ctddh.donDatHang.maDDH = :id")
    List<ChiTietDonDatHang> findAllByDonDatHang(Long id);

}
