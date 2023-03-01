package dev.fastfoodapi.repository;

import dev.fastfoodapi.model.DonDatHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface DonDatHangRepo extends JpaRepository<DonDatHang, Long> {

    @Query("SELECT ddh FROM DonDatHang ddh WHERE ddh.khachHang.userId = :userId")
    List<DonDatHang> findAllByKhachHang(UUID userId);

//    @Query("SELECT ddh FROM DonDatHang ddh WHERE ddh.trangThai = :trangThai")
    @Query(value = "SELECT * FROM db_fastfood.tbl_dondathang WHERE trang_thai = :trangThai AND " +
            "MONTH(ngay_dat_hang) = MONTH(NOW())", nativeQuery = true)
    List<DonDatHang> findAllByTrangThai(String trangThai);

    @Query(value = "SELECT SUM(tong_tien) AS tong_tien, ngay_dat_hang, \n" +
            "MAX(maddh) AS maddh, MAX(dia_chi_giao_hang) AS dia_chi_giao_hang, MAX(ma_giam_gia) AS ma_giam_gia,\n" +
            "MAX(trang_thai) AS trang_thai, MAX(ma_khach_hang) AS ma_khach_hang, MAX(hinh_thuc) AS hinh_thuc\n" +
            "FROM db_fastfood.tbl_dondathang\n" +
            "WHERE MONTH(ngay_dat_hang) = MONTH(NOW()) AND trang_thai = 'Đã thanh toán'\n" +
            "GROUP BY ngay_dat_hang\n" +
            "ORDER BY DAY(ngay_dat_hang) ASC", nativeQuery = true)
    List<DonDatHang> ngayTheoThang();

    @Query(value = "SELECT SUM(tong_tien) AS tong_tien, MAX(ngay_dat_hang) AS ngay_dat_hang,\n" +
            "MAX(maddh) AS maddh, MAX(dia_chi_giao_hang) AS dia_chi_giao_hang, MAX(ma_giam_gia) AS ma_giam_gia,\n" +
            "MAX(trang_thai) AS trang_thai, MAX(ma_khach_hang) AS ma_khach_hang, MAX(hinh_thuc) AS hinh_thuc\n" +
            "FROM db_fastfood.tbl_dondathang\n" +
            "WHERE YEAR(ngay_dat_hang) = YEAR(NOW()) AND trang_thai = 'Đã thanh toán'\n" +
            "GROUP BY MONTH(ngay_dat_hang)\n" +
            "ORDER BY MONTH(ngay_dat_hang) ASC", nativeQuery = true)
    List<DonDatHang> thangTrongNam();


    @Query(value = "SELECT SUM(tong_tien) AS tong_tien, ngay_dat_hang,\n" +
            "MAX(maddh) AS maddh, MAX(dia_chi_giao_hang) AS dia_chi_giao_hang, MAX(ma_giam_gia) AS ma_giam_gia,\n" +
            "MAX(trang_thai) AS trang_thai, MAX(ma_khach_hang) AS ma_khach_hang, MAX(hinh_thuc) AS hinh_thuc\n" +
            "FROM db_fastfood.tbl_dondathang\n" +
            "WHERE MONTH(ngay_dat_hang) = MONTH(NOW()) AND ngay_dat_hang >= DATE_SUB(NOW(), INTERVAL 7 DAY) AND trang_thai = 'Đã thanh toán'\n" +
            "GROUP BY ngay_dat_hang\n" +
            "ORDER BY DAY(ngay_dat_hang) ASC", nativeQuery = true)
    List<DonDatHang> bayNgayGanDay();
}
