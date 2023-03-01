package dev.fastfoodapi.repository;

import dev.fastfoodapi.model.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface NhanVienRepo extends JpaRepository<NhanVien, UUID> {

    @Query("SELECT nv FROM NhanVien nv WHERE nv.username = :s")
    NhanVien findByUsername(String s);

    @Query("SELECT nv.roleName FROM NhanVien nv WHERE nv.username = :s")
    String findRoleNameByUsername(String s);

}
