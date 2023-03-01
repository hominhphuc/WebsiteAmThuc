package dev.fastfoodapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_phieugiamgia")
public class PhieuGiamGia {

    @Id
    private String maGiamGia;

    private String tenPhieu;

    private String loaiPhieu;

    private String trangThai;

    private double phanTram;

    private LocalDate ngayBatDau;

    private LocalDate ngayKetThuc;

    @ManyToOne
    @JoinColumn(name = "ma_khach_hang")
    private KhachHang khachHang;

}
