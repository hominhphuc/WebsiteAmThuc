package dev.fastfoodapi.service;

import dev.fastfoodapi.model.MatHang;
import dev.fastfoodapi.model.ResponseMessage;
import dev.fastfoodapi.repository.MatHangRepo;
import dev.fastfoodapi.service.helper.ExcelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MatHangServiceImpl implements MatHangService{

    @Autowired
    private MatHangRepo matHangRepo;

    //Hàm CRUD mặc định
    @Override
    public MatHang save(MatHang obj) {
        return matHangRepo.save(obj);
    }

    @Override
    public Optional<MatHang> findById(Long id) {
        return matHangRepo.findById(id);
    }

    @Override
    public List<MatHang> findAll() {
        return matHangRepo.findAll();
    }

    @Override
    public MatHang update(Long id, MatHang obj) {
        obj.setMaMH(id);
        return matHangRepo.save(obj);
    }

    @Override
    public void delete(Long id) {
        matHangRepo.deleteById(id);
    }

    //Một số hàm khác được thêm vào
    @Override
    public List<MatHang> findAllByLMH(Long id) {
        return matHangRepo.findAllByLoaiMatHang(id);
    }

    @Override
    public List<MatHang> features() {
        return matHangRepo.features();
    }

    @Override
    public List<MatHang> topSeller() {
        return matHangRepo.topSeller();
    }

    @Override
    public List<MatHang> today() {
        return matHangRepo.today();
    }

    @Override
    public List<MatHang> last7days() {
        return matHangRepo.last7days();
    }

    @Override
    public List<MatHang> thisMonth() {
        return matHangRepo.thisMonth();
    }

    @Override
    public List<MatHang> thisYear() {
        return matHangRepo.thisYear();
    }

    @Override
    public List<MatHang> search(String keyword) {
        return matHangRepo.search(keyword);
    }

    @Override
    public ResponseEntity<ResponseMessage> saveAllByFile(MultipartFile file) {
        String message = "";
        List<MatHang> listBefore = matHangRepo.findAll();
        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                List<MatHang> listAfter = ExcelHelper.excelToProducts(file.getInputStream());
                matHangRepo.saveAll(listAfter);
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
