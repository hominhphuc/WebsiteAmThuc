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
@Table(name = "tbl_gioithieu")
public class GioiThieu {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long maGT;

    private String tieuDe;

    private String noiDung;

    private String ten;

    private String hinhAnh;

}
