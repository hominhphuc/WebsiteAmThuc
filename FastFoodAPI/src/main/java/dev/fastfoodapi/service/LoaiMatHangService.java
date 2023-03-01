package dev.fastfoodapi.service;

import dev.fastfoodapi.model.LoaiMatHang;
import dev.fastfoodapi.model.ResponseMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface LoaiMatHangService {

    //Hàm CRUD mặc định================================
    LoaiMatHang save(LoaiMatHang obj);

    Optional<LoaiMatHang> findById(Long id);

    List<LoaiMatHang> findAll();

    LoaiMatHang update(Long id, LoaiMatHang obj);

    void delete(Long id);

    //Một số hàm khác==================================
    ResponseEntity<ResponseMessage> saveAllByFile(MultipartFile file);

}
