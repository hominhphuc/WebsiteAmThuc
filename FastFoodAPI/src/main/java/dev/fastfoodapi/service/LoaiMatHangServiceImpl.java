package dev.fastfoodapi.service;

import dev.fastfoodapi.model.LoaiMatHang;
import dev.fastfoodapi.model.ResponseMessage;
import dev.fastfoodapi.repository.LoaiMatHangRepo;
import dev.fastfoodapi.service.helper.ExcelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class LoaiMatHangServiceImpl implements LoaiMatHangService{

    @Autowired
    private LoaiMatHangRepo loaiMatHangRepo;

    //Hàm CRUD mặc định ====================================================================
    @Override
    public LoaiMatHang save(LoaiMatHang obj) {
        return loaiMatHangRepo.save(obj);
    }

    @Override
    public Optional<LoaiMatHang> findById(Long id) {
        return loaiMatHangRepo.findById(id);
    }

    @Override
    public List<LoaiMatHang> findAll() {
        return loaiMatHangRepo.findAll();
    }

    @Override
    public LoaiMatHang update(Long id, LoaiMatHang obj) {
        obj.setMaLMH(id);
        return loaiMatHangRepo.save(obj);
    }

    @Override
    public void delete(Long id) {
        loaiMatHangRepo.deleteById(id);
    }

    //Một số hàm khác ========================================================================
    @Override
    public ResponseEntity<ResponseMessage> saveAllByFile(MultipartFile file) {
        String message = "";
        List<LoaiMatHang> listBefore = loaiMatHangRepo.findAll();
        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                List<LoaiMatHang> listAfter = ExcelHelper.excelToCategories(file.getInputStream());
                loaiMatHangRepo.saveAll(listAfter);
                message = "Tải lên thành công! Có " + (listAfter.size() - (listAfter.size() - listBefore.size())) + "" +
                        " dòng được cập nhật và " + (listAfter.size() - listBefore.size()) + " dòng được thêm vào";
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (IOException e) {
                throw new RuntimeException("Lỗi phân tích dữ liệu file excel: " + e.getMessage());
            }
        }

        message = "Thất bại! Xin Vui lòng tải lên bằng file excel (.xlsx)";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }
}
