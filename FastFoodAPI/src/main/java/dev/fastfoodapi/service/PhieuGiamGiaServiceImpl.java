package dev.fastfoodapi.service;

import dev.fastfoodapi.model.PhieuGiamGia;
import dev.fastfoodapi.repository.PhieuGiamGiaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
public class PhieuGiamGiaServiceImpl implements PhieuGiamGiaService{

    @Autowired
    private PhieuGiamGiaRepo phieuGiamGiaRepo;

    @Override
    public PhieuGiamGia save(PhieuGiamGia obj) {
        obj.setMaGiamGia(createRandomCode(6, "SUM", "T&T"));
        return phieuGiamGiaRepo.save(obj);
    }

    @Override
    public Optional<PhieuGiamGia> findById(String id) {
        return phieuGiamGiaRepo.findById(id);
    }

    @Override
    public List<PhieuGiamGia> findAll() {
        return phieuGiamGiaRepo.findAll();
    }

    @Override
    public PhieuGiamGia update(String id, PhieuGiamGia obj) {
        obj.setMaGiamGia(id);
        return phieuGiamGiaRepo.save(obj);
    }

    @Override
    public void delete(String id) {
        phieuGiamGiaRepo.deleteById(id);
    }

    @Override
    public List<PhieuGiamGia> findAllByKhachHang(UUID id) {
        return phieuGiamGiaRepo.findAllByKhachHang(id);
    }

    private String createRandomCode(int codeLength, String first, String middle) {
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new SecureRandom();
        for (int i = 0; i < codeLength; i++) {
            if (i == 0){
                sb.append(first);
            }
            if (i == 1){
                sb.append(middle);
            }
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        String output = sb.toString();
        return output;
    }

}
