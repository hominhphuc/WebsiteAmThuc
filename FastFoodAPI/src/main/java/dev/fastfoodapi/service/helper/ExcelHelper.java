package dev.fastfoodapi.service.helper;

import dev.fastfoodapi.model.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class ExcelHelper {

    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String SHEET1 = "MatHang";
    static String SHEET2 = "LoaiMatHang";
    static String SHEET3 = "GioiThieu";
    static String SHEET4 = "KhachHang";
    static String SHEET5 = "NhanVien";

    public static boolean hasExcelFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    //Import mặt hàng
    public static List<MatHang> excelToProducts(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheet(SHEET1);
            Iterator<Row> rows = sheet.iterator();

            List<MatHang> list = new ArrayList<>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0 || rowNumber == 1) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                MatHang obj = new MatHang();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            obj.setMaMH((long) currentCell.getNumericCellValue());
                            break;

                        case 1:
                            obj.setHinhAnh(currentCell.getStringCellValue());
                            break;

                        case 2:
                            obj.setTenMH(currentCell.getStringCellValue());
                            break;

                        case 3:
                            obj.setMoTa(currentCell.getStringCellValue());
                            break;

                        case 4:
                            obj.setDonViTinh(currentCell.getStringCellValue());
                            break;

                        case 5:
                            obj.setDonGia(currentCell.getNumericCellValue());
                            break;

                        case 6:
                            obj.setLoaiMatHang(new LoaiMatHang().builder().maLMH((long) currentCell.getNumericCellValue()).build());
                            break;

                        default:
                            break;
                    }

                    cellIdx++;
                }

                list.add(obj);
            }

            workbook.close();

            return list;
        } catch (IOException e) {
            throw new RuntimeException("Lỗi phân tích file Excel: " + e.getMessage());
        }
    }

    //Import loại mặt hàng
    public static List<LoaiMatHang> excelToCategories(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheet(SHEET2);
            Iterator<Row> rows = sheet.iterator();

            List<LoaiMatHang> list = new ArrayList<>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0 || rowNumber == 1) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                LoaiMatHang obj = new LoaiMatHang();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            obj.setMaLMH((long) currentCell.getNumericCellValue());
                            break;

                        case 1:
                            obj.setTenLMH(currentCell.getStringCellValue());
                            break;

                        default:
                            break;
                    }

                    cellIdx++;
                }

                list.add(obj);
            }

            workbook.close();

            return list;
        } catch (IOException e) {
            throw new RuntimeException("Lỗi phân tích file Excel: " + e.getMessage());
        }
    }

    //Import bảng giới thiệu
    public static List<GioiThieu> excelToIntro(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheet(SHEET3);
            Iterator<Row> rows = sheet.iterator();

            List<GioiThieu> list = new ArrayList<>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0 || rowNumber == 1) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                GioiThieu obj = new GioiThieu();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            obj.setMaGT((long) currentCell.getNumericCellValue());
                            break;

                        case 1:
                            obj.setHinhAnh(currentCell.getStringCellValue());
                            break;

                        case 2:
                            obj.setTen(currentCell.getStringCellValue());
                            break;

                        case 3:
                            obj.setTieuDe(currentCell.getStringCellValue());
                            break;

                        case 4:
                            obj.setNoiDung(currentCell.getStringCellValue());
                            break;

                        default:
                            break;
                    }

                    cellIdx++;
                }

                list.add(obj);
            }

            workbook.close();

            return list;
        } catch (IOException e) {
            throw new RuntimeException("Lỗi phân tích file Excel: " + e.getMessage());
        }
    }

    //Import bảng khách hàng
    public static List<KhachHang> excelToClients(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheet(SHEET4);
            Iterator<Row> rows = sheet.iterator();

            List<KhachHang> list = new ArrayList<>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0 || rowNumber == 1) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                KhachHang obj = new KhachHang();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            try {
                                obj.setUserId(UUID.fromString(currentCell.getStringCellValue()));
                            } catch (Exception e) {
                                obj.setUserId(UUID.randomUUID());
                            }
                            break;

                        case 1:
                            obj.setAvatar(currentCell.getStringCellValue());
                            break;

                        case 2:
                            obj.setName(currentCell.getStringCellValue());
                            break;

                        case 3:
                            obj.setDiemTichLuy((int) currentCell.getNumericCellValue());
                            break;

                        case 4:
                            obj.setAddress(currentCell.getStringCellValue());
                            break;

                        case 5:
                            boolean gender = false;
                            if ("Nam".equals(currentCell.getStringCellValue())) {
                                gender = true;
                            } else if ("Nữ".equals(currentCell.getStringCellValue())) {
                                gender = false;
                            }
                            obj.setGender(gender);
                            break;

                        case 6:
                            obj.setEmail(currentCell.getStringCellValue());
                            break;

                        case 7:
                            obj.setBirthDate(currentCell.getLocalDateTimeCellValue().toLocalDate());
                            break;

                        case 8:
                            obj.setPhone(currentCell.getStringCellValue());
                            break;

                        default:
                            break;
                    }

                    cellIdx++;
                }

                list.add(obj);
            }

            workbook.close();

            return list;
        } catch (IOException e) {
            throw new RuntimeException("Lỗi phân tích file Excel: " + e.getMessage());
        }
    }

    //Import bảng nhân viên
    public static List<NhanVien> excelToStaff(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheet(SHEET5);
            Iterator<Row> rows = sheet.iterator();

            List<NhanVien> list = new ArrayList<>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0 || rowNumber == 1) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                NhanVien obj = new NhanVien();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            try {
                                obj.setUserId(UUID.fromString(currentCell.getStringCellValue()));
                            } catch (Exception e) {
                                obj.setUserId(UUID.randomUUID());
                            }
                            break;

                        case 1:
                            obj.setAvatar(currentCell.getStringCellValue());
                            break;

                        case 2:
                            obj.setName(currentCell.getStringCellValue());
                            break;

                        case 3:
                            obj.setRoleName(currentCell.getStringCellValue());
                            break;

                        case 4:
                            obj.setAddress(currentCell.getStringCellValue());
                            break;

                        case 5:
                            boolean gender = false;
                            if ("Nam".equals(currentCell.getStringCellValue())) {
                                gender = true;
                            } else if ("Nữ".equals(currentCell.getStringCellValue())) {
                                gender = false;
                            }
                            obj.setGender(gender);
                            break;

                        case 6:
                            obj.setEmail(currentCell.getStringCellValue());
                            break;

                        case 7:
                            obj.setBirthDate(currentCell.getLocalDateTimeCellValue().toLocalDate());
                            break;

                        case 8:
                            obj.setPhone(currentCell.getStringCellValue());
                            break;

                        default:
                            break;
                    }

                    cellIdx++;
                }

                list.add(obj);
            }

            workbook.close();

            return list;
        } catch (IOException e) {
            throw new RuntimeException("Lỗi phân tích file Excel: " + e.getMessage());
        }
    }
}
