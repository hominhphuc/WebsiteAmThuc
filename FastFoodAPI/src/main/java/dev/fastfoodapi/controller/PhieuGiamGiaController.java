package dev.fastfoodapi.controller;

import dev.fastfoodapi.model.PhieuGiamGia;
import dev.fastfoodapi.service.PhieuGiamGiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/voucher")
public class PhieuGiamGiaController {

    @Autowired
    private PhieuGiamGiaService phieuGiamGiaService;

    //Hàm CRUD mặc định
    @GetMapping
    public List<PhieuGiamGia> findAll() {
        return phieuGiamGiaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhieuGiamGia> findById(@PathVariable(value = "id") String id) {
        try {
            PhieuGiamGia obj = phieuGiamGiaService.findById(id).get();
            return ResponseEntity.ok().body(obj);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public PhieuGiamGia create(@RequestBody PhieuGiamGia obj) {
        return phieuGiamGiaService.save(obj);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PhieuGiamGia> update(@RequestBody PhieuGiamGia obj, @PathVariable("id") String id) {
        PhieuGiamGia o = phieuGiamGiaService.update(id, obj);
        return ResponseEntity.ok(o);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PhieuGiamGia> delete(@PathVariable("id") String id) {
        try {
            phieuGiamGiaService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/maKhachHang={id}")
    public List<PhieuGiamGia> findAllByKhachHang(@PathVariable UUID id) {
        return phieuGiamGiaService.findAllByKhachHang(id);
    }

}
