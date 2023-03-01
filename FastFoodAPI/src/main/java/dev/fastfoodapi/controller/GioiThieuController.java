package dev.fastfoodapi.controller;

import dev.fastfoodapi.model.GioiThieu;

import dev.fastfoodapi.model.ResponseMessage;
import dev.fastfoodapi.service.GioiThieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/gioi-thieu")
public class GioiThieuController {

    @Autowired
    private GioiThieuService gioiThieuService;

    //Hàm CRUD mặc định============================================================================================
    @GetMapping
    public List<GioiThieu> getAllGioHang() {
        return gioiThieuService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GioiThieu> getGioHangById(@PathVariable(value = "id") Long id) {
        try {
            GioiThieu obj = gioiThieuService.findById(id).get();
            return ResponseEntity.ok().body(obj);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public GioiThieu createGioHang(@RequestBody GioiThieu obj) {
        return gioiThieuService.save(obj);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GioiThieu> updateGioHang(@RequestBody GioiThieu obj, @PathVariable("id") Long id) {
        GioiThieu o = gioiThieuService.update(id, obj);
        return ResponseEntity.ok(o);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GioiThieu> deleteGioHang(@PathVariable("id") Long id) {
        try {
            gioiThieuService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    //Một số hàm khác ==============================================================================================
    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        return gioiThieuService.saveAllByFile(file);
    }
}
