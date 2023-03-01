package dev.fastfoodapi.controller;

import dev.fastfoodapi.model.MatHang;
import dev.fastfoodapi.model.ResponseMessage;
import dev.fastfoodapi.service.MatHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/mat-hang")
public class MatHangController {

    @Autowired
    private MatHangService matHangService;

    //Hàm CRUD mặc định
    @GetMapping
    public List<MatHang> getAllMatHang() {
        return matHangService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatHang> getMatHangById(@PathVariable(value = "id") Long id) {
        try {
            MatHang obj = matHangService.findById(id).get();
            return ResponseEntity.ok().body(obj);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public MatHang createMatHang(@RequestBody MatHang obj) {
        return matHangService.save(obj);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MatHang> updateMatHang(@RequestBody MatHang obj, @PathVariable("id") Long id) {
        MatHang o = matHangService.update(id, obj);
        return ResponseEntity.ok(o);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MatHang> deleteMatHang(@PathVariable("id") Long id) {
        try {
            matHangService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    //Một số hàm khác được thêm vào
    @GetMapping("/pLoai={id}")
    public List<MatHang> getAllMatHangByLMH(@PathVariable(value = "id") Long id) {
        return matHangService.findAllByLMH(id);
    }

    @GetMapping("/features")
    public List<MatHang> getFeatures() {
        return matHangService.features();
    }

    @GetMapping("/top-seller")
    public List<MatHang> getTopSeller() {
        return matHangService.topSeller();
    }

    @GetMapping("/today")
    public List<MatHang> today() {
        return matHangService.today();
    }

    @GetMapping("/last7days")
    public List<MatHang> last7days() {
        return matHangService.last7days();
    }

    @GetMapping("/thisMonth")
    public List<MatHang> thisMonth() {
        return matHangService.thisMonth();
    }

    @GetMapping("/thisYear")
    public List<MatHang> thisYear() {
        return matHangService.thisYear();
    }

    @GetMapping("/keyword={keyword}")
    public List<MatHang> search(@PathVariable(value = "keyword") String keyword) {
        return matHangService.search(keyword);
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        return matHangService.saveAllByFile(file);
    }
}
