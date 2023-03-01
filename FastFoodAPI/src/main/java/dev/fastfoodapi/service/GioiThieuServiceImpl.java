package dev.fastfoodapi.service;

import dev.fastfoodapi.model.GioiThieu;
import dev.fastfoodapi.model.ResponseMessage;
import dev.fastfoodapi.repository.GioiThieuRepo;
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
public class GioiThieuServiceImpl implements GioiThieuService{

    @Autowired
    private GioiThieuRepo gioiThieuRepo;

    @Override
    public GioiThieu save(GioiThieu obj) {
        return gioiThieuRepo.save(obj);
    }

    @Override
    public Optional<GioiThieu> findById(Long id) {
        return gioiThieuRepo.findById(id);
    }

    @Override
    public List<GioiThieu> findAll() {
        return gioiThieuRepo.findAll();
    }

    @Override
    public GioiThieu update(Long id, GioiThieu obj) {
        obj.setMaGT(id);
        return gioiThieuRepo.save(obj);
    }

    @Override
    public void delete(Long id) {
        gioiThieuRepo.deleteById(id);
    }

    //Một số hàm khác ========================================================================
    @Override
    public ResponseEntity<ResponseMessage> saveAllByFile(MultipartFile file) {
        String message = "";
        List<GioiThieu> listBefore = gioiThieuRepo.findAll();
        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                List<GioiThieu> listAfter = ExcelHelper.excelToIntro(file.getInputStream());
                gioiThieuRepo.saveAll(listAfter);
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
