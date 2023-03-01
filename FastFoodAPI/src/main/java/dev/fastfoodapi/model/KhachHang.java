package dev.fastfoodapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_khachhang")
public class KhachHang extends User {

    private int diemTichLuy;

    @JsonIgnore
    @OneToMany(mappedBy = "khachHang")
    private List<DonDatHang> donDatHangList;

    @JsonIgnore
    @OneToMany(mappedBy = "khachHang", cascade = CascadeType.ALL)
    private List<PhieuGiamGia> phieuGiamGiaList;
}
