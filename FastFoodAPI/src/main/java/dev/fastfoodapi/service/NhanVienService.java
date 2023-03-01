package dev.fastfoodapi.service;

import dev.fastfoodapi.model.NhanVien;
import dev.fastfoodapi.model.ResponseMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NhanVienService {

    //Hàm CRUD mặc định
    NhanVien save(NhanVien obj);

    Optional<NhanVien> findById(UUID id);

    List<NhanVien> findAll();

    NhanVien update(UUID id, NhanVien obj);

    void delete(UUID id);

    //Một số hàm khác
    NhanVien findByUsername(String s);

    String findRoleNameByUsername(String s);

    boolean existsByUsername(String s);

    ResponseEntity<ResponseMessage> saveAllByFile(MultipartFile file);
}
