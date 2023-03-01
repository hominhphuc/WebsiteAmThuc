package dev.fastfoodapi.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_dondathang")
public class DonDatHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long maDDH;

    private LocalDate ngayDatHang;

    private String trangThai;

    private String diaChiGiaoHang;

    private String hinhThuc;

    private Double tongTien;

    private String maGiamGia;

    @JsonIgnore
    @OneToMany(mappedBy = "donDatHang", cascade = CascadeType.ALL)
    private List<ChiTietDonDatHang> chiTietDonDatHangList;

    @ManyToOne
    @JoinColumn(name = "ma_khach_hang")
    private KhachHang khachHang;

}
