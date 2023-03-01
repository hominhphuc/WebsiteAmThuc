package dev.fastfoodapi.service;

import dev.fastfoodapi.model.GioiThieu;
import dev.fastfoodapi.model.ResponseMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface GioiThieuService {

    //Hàm CRUD mặc định
    GioiThieu save(GioiThieu obj);

    Optional<GioiThieu> findById(Long id);

    List<GioiThieu> findAll();

    GioiThieu update(Long id, GioiThieu obj);

    void delete(Long id);

    //Một số hàm khác==================================
    ResponseEntity<ResponseMessage> saveAllByFile(MultipartFile file);

}
