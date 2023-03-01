package dev.fastfoodapi.service;

import dev.fastfoodapi.model.KhachHang;
import dev.fastfoodapi.model.NhanVien;
import dev.fastfoodapi.model.ResponseMessage;
import dev.fastfoodapi.repository.NhanVienRepo;
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
public class NhanVienServiceImpl implements NhanVienService {

    @Autowired
    private NhanVienRepo nhanVienRepo;

    //Hàm CRUD mặc định
    @Override
    public NhanVien save(NhanVien obj) {
        return nhanVienRepo.save(obj);
    }

    @Override
    public Optional<NhanVien> findById(UUID id) {
        return nhanVienRepo.findById(id);
    }

    @Override
    public List<NhanVien> findAll() {
        return nhanVienRepo.findAll();
    }

    @Override
    public NhanVien update(UUID id, NhanVien obj) {
        obj.setUserId(id);
        return nhanVienRepo.save(obj);
    }

    @Override
    public void delete(UUID id) {
        nhanVienRepo.deleteById(id);
    }

    //Một số hàm khác
    @Override
    public NhanVien findByUsername(String s) {
        return nhanVienRepo.findByUsername(s);
    }

    @Override
    public String findRoleNameByUsername(String s) {
        return nhanVienRepo.findRoleNameByUsername(s);
    }

    @Override
    public boolean existsByUsername(String s) {
        if (nhanVienRepo.findByUsername(s) == null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ResponseEntity<ResponseMessage> saveAllByFile(MultipartFile file) {
        String message = "";
        List<NhanVien> listBefore = nhanVienRepo.findAll();
        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                List<NhanVien> listAfter = ExcelHelper.excelToStaff(file.getInputStream());
                for (NhanVien nhanVien : listAfter) {
                    try {
                        if (nhanVien.getUserId().toString() != "0") {
                            NhanVien kh = nhanVienRepo.findById(nhanVien.getUserId()).get();
                            nhanVien.setUsername(kh.getUsername());
                            nhanVien.setPassword(kh.getPassword());
                            nhanVien.setRoleName(kh.getRoleName());
                        }
                    } catch (Exception e) {
                        continue;
                    }
                }
                nhanVienRepo.saveAll(listAfter);
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
