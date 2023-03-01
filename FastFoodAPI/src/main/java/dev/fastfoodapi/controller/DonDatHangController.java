package dev.fastfoodapi.controller;

import dev.fastfoodapi.model.DonDatHang;
import dev.fastfoodapi.service.DonDatHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/don-dat-hang")
public class DonDatHangController {

    @Autowired
    private DonDatHangService donDatHangService;

    //Hàm CRUD mặc định ===============================================================================================
    @GetMapping
    public List<DonDatHang> getAllDonDatHang() {
        return donDatHangService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DonDatHang> getDonDatHangById(@PathVariable(value = "id") Long id) {
        try {
            DonDatHang obj = donDatHangService.findById(id).get();
            return ResponseEntity.ok().body(obj);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public DonDatHang createDonDatHang(@RequestBody DonDatHang obj) {
        obj.setNgayDatHang(LocalDate.now());
        return donDatHangService.save(obj);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DonDatHang> updateDonDatHang(@RequestBody DonDatHang obj, @PathVariable("id") Long id) {
        DonDatHang o = donDatHangService.update(id, obj);
        return ResponseEntity.ok(o);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DonDatHang> deleteDonDatHang(@PathVariable("id") Long id) {
        try {
            donDatHangService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    //Một số hàm khác ================================================================================================
    @GetMapping("/userId={id}")
    public List<DonDatHang> getAllByKhachHang(@PathVariable("id") UUID userId) {
        int size = donDatHangService.findAllByKhachHang(userId).size();
        return donDatHangService.findAllByKhachHang(userId);
    }

    @GetMapping("/trangThai")
    public List<DonDatHang> getAllByTrangThai(@RequestParam String status) {
        return donDatHangService.findAllByTrangThai(status);
    }

    @GetMapping("/ngayTheoThang")
    public List<DonDatHang> getNgayTheoThang() {
        return donDatHangService.ngayTheoThang();
    }

    @GetMapping("/thangTrongNam")
    public List<DonDatHang> getThangTrongNam() {
        return donDatHangService.thangTrongNam();
    }

    @GetMapping("/bayNgayGanDay")
    public List<DonDatHang> getBayNgayGanDay() {
        return donDatHangService.bayNgayGanDay();
    }
}
