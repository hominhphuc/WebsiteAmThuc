package dev.fastfoodapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_loaimathang")
public class LoaiMatHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long maLMH;

    private String tenLMH;

    @JsonIgnore
    @OneToMany(mappedBy = "loaiMatHang", cascade = CascadeType.ALL)
    private List<MatHang> matHangList;
}
