package dev.fastfoodapi.controller;

import dev.fastfoodapi.model.ChiTietDonDatHang;
import dev.fastfoodapi.service.ChiTietDonDatHangService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/chi-tiet-don-dat-hang")
public class ChiTietDonDatHangController {

    @Autowired
    private ChiTietDonDatHangService chiTietDonDatHangService;

    //Hàm CRUD mặc định ======================================================================================
    @GetMapping
    public List<ChiTietDonDatHang> getAllChiTietDonDatHang() {
        return chiTietDonDatHangService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChiTietDonDatHang> getChiTietDonDatHangById(@PathVariable(value = "id") Long id) {
        try {
            ChiTietDonDatHang obj = chiTietDonDatHangService.findById(id).get();
            return ResponseEntity.ok().body(obj);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ChiTietDonDatHang createChiTietDonDatHang(@RequestBody ChiTietDonDatHang obj) {
        return chiTietDonDatHangService.save(obj);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChiTietDonDatHang> updateChiTietDonDatHang(@RequestBody ChiTietDonDatHang obj, @PathVariable("id") Long id) {
        ChiTietDonDatHang o = chiTietDonDatHangService.update(id, obj);
        return ResponseEntity.ok(o);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ChiTietDonDatHang> deleteChiTietDonDatHang(@PathVariable("id") Long id) {
        try {
            chiTietDonDatHangService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    //Một số hàm khác ======================================================================================
    @GetMapping("/donDatHang={id}")
    public List<ChiTietDonDatHang> getAllByDonDatHang(@PathVariable("id") Long id) {
        return chiTietDonDatHangService.findAllByDonDatHang(id);
    }

}
