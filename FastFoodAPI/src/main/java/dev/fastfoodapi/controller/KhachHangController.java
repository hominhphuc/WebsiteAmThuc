package dev.fastfoodapi.controller;

import dev.fastfoodapi.model.KhachHang;
import dev.fastfoodapi.model.ResponseMessage;
import dev.fastfoodapi.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/khach-hang")
public class KhachHangController {

    @Autowired
    private KhachHangService khachHangService;

    //Hàm CRUD mặc định
    @GetMapping
    public List<KhachHang> getAllKhachHang() {
        return khachHangService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<KhachHang> getKhachHangById(@PathVariable(value = "id") UUID id) {
        try {
            KhachHang obj = khachHangService.findById(id).get();
            return ResponseEntity.ok().body(obj);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public KhachHang createKhachHang(@RequestBody KhachHang obj) {
        return khachHangService.save(obj);
    }

    @PutMapping("/{id}")
    public ResponseEntity<KhachHang> updateKhachHang(@RequestBody KhachHang obj, @PathVariable("id") UUID id) {
        KhachHang o = khachHangService.update(id, obj);
        return ResponseEntity.ok(o);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<KhachHang> deleteKhachHang(@PathVariable("id") UUID id) {
        try {
            khachHangService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    //Một số hàm khác
    @GetMapping("/phone={phone}")
    public KhachHang getKhachHangByPhone(@PathVariable(value = "phone") String s) {
        return khachHangService.findByPhone(s);
    }

    @GetMapping("/email={email}")
    public KhachHang getCustomerByEmail(@PathVariable(value = "email") String email) {
        return khachHangService.findByEmail(email);
    }

    @PostMapping("/checkExistsByPhone")
    public boolean existsByUsername(@RequestParam String p){
        return khachHangService.existsByPhone(p);
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        return khachHangService.saveAllByFile(file);
    }
}
