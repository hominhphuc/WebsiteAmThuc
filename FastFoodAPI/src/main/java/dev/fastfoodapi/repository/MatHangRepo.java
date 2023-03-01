package dev.fastfoodapi.repository;

import dev.fastfoodapi.model.MatHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MatHangRepo extends JpaRepository<MatHang, Long> {

    @Query("SELECT mh FROM MatHang mh WHERE mh.loaiMatHang.maLMH = :id")
    List<MatHang> findAllByLoaiMatHang(Long id);

    @Query("SELECT mh FROM MatHang mh WHERE mh.tenMH LIKE %?1%")
    List<MatHang> search(String keyword);

    @Query("SELECT mh FROM MatHang mh WHERE mh.moTa LIKE '%phô mai%'")
    List<MatHang> features();

    @Query(value = "SELECT mh.mamh, SUM(so_luong_dat) AS so_luong_dat, mh.don_gia, mh.don_vi_tinh, mh.ma_lmh, mh.mo_ta, mh.tenmh, mh.hinh_anh \n" +
            "FROM db_fastfood.tbl_mathang mh JOIN db_fastfood.tbl_chitietdondathang ct \n" +
            "ON ct.ma_mat_hang = mh.mamh\n" +
            "GROUP BY mh.mamh, mh.don_gia, mh.don_vi_tinh, mh.ma_lmh, mh.mo_ta, mh.tenmh, mh.hinh_anh\n" +
            "ORDER BY SUM(so_luong_dat) \n" +
            "DESC LIMIT 8", nativeQuery = true)
    List<MatHang> topSeller();

    @Query(value = "SELECT mh.mamh, SUM(so_luong_dat) AS don_gia, \n" +
            "mh.don_vi_tinh, mh.ma_lmh, mh.mo_ta, mh.tenmh, mh.hinh_anh\n" +
            "FROM db_fastfood.tbl_mathang mh \n" +
            "JOIN db_fastfood.tbl_chitietdondathang ct ON ct.ma_mat_hang = mh.mamh\n" +
            "JOIN db_fastfood.tbl_dondathang dh ON dh.maddh = ct.ma_don_dat_hang\n" +
            "WHERE dh.ngay_dat_hang = current_date() AND trang_thai = 'Đã thanh toán'\n" +
            "GROUP BY mh.mamh, mh.don_vi_tinh, mh.ma_lmh, mh.mo_ta, mh.tenmh, mh.hinh_anh\n" +
            "ORDER BY SUM(so_luong_dat)\n" +
            "DESC LIMIT 8", nativeQuery = true)
    List<MatHang> today();

    @Query(value = "SELECT mh.mamh, SUM(so_luong_dat) AS don_gia, \n" +
            "mh.don_vi_tinh, mh.ma_lmh, mh.mo_ta, mh.tenmh, mh.hinh_anh\n" +
            "FROM db_fastfood.tbl_mathang mh \n" +
            "JOIN db_fastfood.tbl_chitietdondathang ct ON ct.ma_mat_hang = mh.mamh\n" +
            "JOIN db_fastfood.tbl_dondathang dh ON dh.maddh = ct.ma_don_dat_hang\n" +
            "WHERE MONTH(ngay_dat_hang) = MONTH(NOW()) AND ngay_dat_hang >= DATE_SUB(NOW(), INTERVAL 7 DAY) AND trang_thai = 'Đã thanh toán'\n" +
            "GROUP BY mh.mamh, mh.don_vi_tinh, mh.ma_lmh, mh.mo_ta, mh.tenmh, mh.hinh_anh\n" +
            "ORDER BY SUM(so_luong_dat)\n" +
            "DESC LIMIT 8", nativeQuery = true)
    List<MatHang> last7days();

    @Query(value = "SELECT mh.mamh, SUM(so_luong_dat) AS don_gia, \n" +
            "mh.don_vi_tinh, mh.ma_lmh, mh.mo_ta, mh.tenmh, mh.hinh_anh\n" +
            "FROM db_fastfood.tbl_mathang mh \n" +
            "JOIN db_fastfood.tbl_chitietdondathang ct ON ct.ma_mat_hang = mh.mamh\n" +
            "JOIN db_fastfood.tbl_dondathang dh ON dh.maddh = ct.ma_don_dat_hang\n" +
            "WHERE MONTH(ngay_dat_hang) = MONTH(NOW()) AND trang_thai = 'Đã thanh toán'\n" +
            "GROUP BY mh.mamh, mh.don_vi_tinh, mh.ma_lmh, mh.mo_ta, mh.tenmh, mh.hinh_anh\n" +
            "ORDER BY SUM(so_luong_dat)\n" +
            "DESC LIMIT 8", nativeQuery = true)
    List<MatHang> thisMonth();

    @Query(value = "SELECT mh.mamh, SUM(so_luong_dat) AS don_gia, \n" +
            "mh.don_vi_tinh, mh.ma_lmh, mh.mo_ta, mh.tenmh, mh.hinh_anh\n" +
            "FROM db_fastfood.tbl_mathang mh \n" +
            "JOIN db_fastfood.tbl_chitietdondathang ct ON ct.ma_mat_hang = mh.mamh\n" +
            "JOIN db_fastfood.tbl_dondathang dh ON dh.maddh = ct.ma_don_dat_hang\n" +
            "WHERE YEAR(ngay_dat_hang) = YEAR(NOW()) AND trang_thai = 'Đã thanh toán'\n" +
            "GROUP BY mh.mamh, mh.don_vi_tinh, mh.ma_lmh, mh.mo_ta, mh.tenmh, mh.hinh_anh\n" +
            "ORDER BY SUM(so_luong_dat)\n" +
            "DESC LIMIT 8", nativeQuery = true)
    List<MatHang> thisYear();
}
