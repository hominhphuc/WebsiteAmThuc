package dev.fastfoodapi.repository;

import dev.fastfoodapi.model.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface KhachHangRepo extends JpaRepository<KhachHang, UUID> {

    @Query("SELECT kh FROM KhachHang kh WHERE kh.phone = :s")
    KhachHang findByPhone(String s);

    @Query("SELECT kh FROM KhachHang kh WHERE kh.email = :email")
    KhachHang findByEmail(String email);

}
