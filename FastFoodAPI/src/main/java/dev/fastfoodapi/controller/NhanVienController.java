package dev.fastfoodapi.controller;

import dev.fastfoodapi.model.NhanVien;
import dev.fastfoodapi.model.ResponseMessage;
import dev.fastfoodapi.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/nhan-vien")
public class NhanVienController {

    @Autowired
    private NhanVienService nhanVienService;

    //Hàm CRUD mặc định
    @GetMapping
    public List<NhanVien> getAllNV() {
        return nhanVienService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<NhanVien> getNVById(@PathVariable(value = "id") UUID id) {
        try {
            NhanVien obj = nhanVienService.findById(id).get();
            return ResponseEntity.ok().body(obj);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public NhanVien createNV(@RequestBody NhanVien obj) {
        return nhanVienService.save(obj);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NhanVien> updateNV(@RequestBody NhanVien obj, @PathVariable("id") UUID id) {
        NhanVien o = nhanVienService.update(id, obj);
        return ResponseEntity.ok(o);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<NhanVien> deleteNV(@PathVariable("id") UUID id) {
        try {
            nhanVienService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    //Một số hàm khác
    @GetMapping("/username={username}")
    public NhanVien getNVByUsername(@PathVariable(value = "username") String s) {
        return nhanVienService.findByUsername(s);
    }

    @GetMapping("/roleName/username={username}")
    public String getRoleNameByUsername(@PathVariable(value = "username") String s) {
        return nhanVienService.findRoleNameByUsername(s);
    }

    @PostMapping("/checkExistsByUsername")
    public boolean existsByUsername(@RequestParam String u){
        return nhanVienService.existsByUsername(u);
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        return nhanVienService.saveAllByFile(file);
    }
}
