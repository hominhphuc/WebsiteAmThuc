package dev.fastfoodapi.service;

import dev.fastfoodapi.model.MatHang;
import dev.fastfoodapi.model.ResponseMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MatHangService {

    //Hàm CRUD mặc định
    MatHang save(MatHang obj);

    Optional<MatHang> findById(Long id);

    List<MatHang> findAll();

    MatHang update(Long id, MatHang obj);

    void delete(Long id);

    //Một số hàm khác được thêm vào
    List<MatHang> findAllByLMH(Long id);

    List<MatHang> features();

    List<MatHang> topSeller();

    List<MatHang> today();

    List<MatHang> last7days();

    List<MatHang> thisMonth();

    List<MatHang> thisYear();

    List<MatHang> search(String keyword);

    ResponseEntity<ResponseMessage> saveAllByFile(MultipartFile file);
}
