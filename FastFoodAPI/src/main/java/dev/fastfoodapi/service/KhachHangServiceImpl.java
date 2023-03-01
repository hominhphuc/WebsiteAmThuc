package dev.fastfoodapi.service;

import dev.fastfoodapi.model.KhachHang;
import dev.fastfoodapi.model.ResponseMessage;
import dev.fastfoodapi.repository.KhachHangRepo;
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
public class KhachHangServiceImpl implements KhachHangService {

    @Autowired
    private KhachHangRepo khachHangRepo;

    //Hàm CRUD mặc định
    @Override
    public KhachHang save(KhachHang obj) {
        return khachHangRepo.save(obj);
    }

    @Override
    public Optional<KhachHang> findById(UUID id) {
        return khachHangRepo.findById(id);
    }

    @Override
    public List<KhachHang> findAll() {
        return khachHangRepo.findAll();
    }

    @Override
    public KhachHang update(UUID id, KhachHang obj) {
        obj.setUserId(id);
        return khachHangRepo.save(obj);
    }

    @Override
    public void delete(UUID id) {
        khachHangRepo.deleteById(id);
    }

    //Một số hàm khác
    @Override
    public KhachHang findByPhone(String s) {
        return khachHangRepo.findByPhone(s);
    }

    @Override
    public KhachHang findByEmail(String email) {
        return khachHangRepo.findByEmail(email);
    }

    @Override
    public boolean existsByPhone(String s) {
        if (khachHangRepo.findByPhone(s) == null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ResponseEntity<ResponseMessage> saveAllByFile(MultipartFile file) {
        String message = "";
        List<KhachHang> listBefore = khachHangRepo.findAll();
        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                List<KhachHang> listAfter = ExcelHelper.excelToClients(file.getInputStream());
                for (KhachHang khachHang : listAfter) {
                    try {
                        if (khachHang.getUserId().toString() != "0") {
                            KhachHang kh = khachHangRepo.findById(khachHang.getUserId()).get();
                            khachHang.setPassword(kh.getPassword());
                            khachHang.setRoleName(kh.getRoleName());
                        }
                    } catch (Exception e) {
                        continue;
                    }
                }
                khachHangRepo.saveAll(listAfter);
                if ((listAfter.size() - listBefore.size()) <= 0) {
                    message = "Tải lên thành công! Có " + (listAfter.size() - (listAfter.size() - listBefore.size())) + "" +
                            " dòng được cập nhật và 0 dòng được thêm vào";
                } else {
                    message = "Tải lên thành công! Có " + listAfter.size() + "" +
                            " dòng được cập nhật và " + (listAfter.size() - listBefore.size()) + " dòng được thêm vào";
                }
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (IOException e) {
                throw new RuntimeException("Lỗi phân tích dữ liệu file excel: " + e.getMessage());
            }
        }

        message = "Thất bại! Xin Vui lòng tải lên bằng file excel (.xlsx)";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }
}
