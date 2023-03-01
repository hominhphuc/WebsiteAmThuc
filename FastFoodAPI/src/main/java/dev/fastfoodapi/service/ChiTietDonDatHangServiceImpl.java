package dev.fastfoodapi.service;

import dev.fastfoodapi.model.ChiTietDonDatHang;
import dev.fastfoodapi.repository.ChiTietDonDatHangRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChiTietDonDatHangServiceImpl implements ChiTietDonDatHangService{

    @Autowired
    private ChiTietDonDatHangRepo chiTietDonDatHangRepo;

    //Hàm CRUD mặc định ===================================================================================
    @Override
    public ChiTietDonDatHang save(ChiTietDonDatHang obj) {
        return chiTietDonDatHangRepo.save(obj);
    }

    @Override
    public Optional<ChiTietDonDatHang> findById(Long id) {
        return chiTietDonDatHangRepo.findById(id);
    }

    @Override
    public List<ChiTietDonDatHang> findAll() {
        return chiTietDonDatHangRepo.findAll();
    }

    @Override
    public ChiTietDonDatHang update(Long id, ChiTietDonDatHang obj) {
        obj.setMaCTDDH(id);
        return chiTietDonDatHangRepo.save(obj);
    }

    @Override
    public void delete(Long id) {
        chiTietDonDatHangRepo.deleteById(id);
    }

    //Một số hàm khác ===================================================================================
    @Override
    public List<ChiTietDonDatHang> findAllByDonDatHang(Long id) {
        return chiTietDonDatHangRepo.findAllByDonDatHang(id);
    }

}
