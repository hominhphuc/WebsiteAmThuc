package dev.fastfoodapi.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_chitietdondathang")
public class ChiTietDonDatHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long maCTDDH;

    private Double donGia;

    private int soLuongDat;

    @ManyToOne
    @JoinColumn(name = "ma_mat_hang")
    private MatHang matHang;

    @ManyToOne
    @JoinColumn(name = "ma_don_dat_hang")
    private DonDatHang donDatHang;
}
