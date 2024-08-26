package my.demo.office.excel;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;


@Slf4j
public class ExcelReader {
    private final static String sqlTemplate = "update back3d_account.tb_bx_bk_acc_user set EMPLOYEE_NO='%s' where USER_UID='%s';%n";

    public static void main(String[] args) {
        String excelFilePath = "/Users/wyb/IdeaProjects/back3d/data/创元用户.xlsx";
        excel2sql(excelFilePath);
        //readExcel(excelFilePath);
    }

    public static void excel2sql(String excelFilePath) {
        try (FileInputStream fis = new FileInputStream(excelFilePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            // 获取第一个工作表
            Sheet sheet = workbook.getSheetAt(0);

            // 遍历每一行
            int index = 0;
            for (Row row : sheet) {
                if (index == 0) {
                    index++;
                    continue;
                }

                String USER_UID = cellValue2String(row.getCell(1)).trim();
                String EMPLOYEE_NO = cellValue2String(row.getCell(4)).trim();

                if (Objects.equals(EMPLOYEE_NO, "无") || Objects.equals(EMPLOYEE_NO, "已离职")) {
                    continue;
                }

                System.out.printf(sqlTemplate, EMPLOYEE_NO, USER_UID);
                //System.out.printf("%s %s %n", USER_UID, EMPLOYEE_NO);
            }
        } catch (IOException e) {
            //e.printStackTrace();
            log.error(e.getMessage(), e);
        }
    }

    public static String cellValue2String(Cell cell) {
        if (cell == null) {
            return "";
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    double value = cell.getNumericCellValue();
                    return String.format("%.0f", value);
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "UNKNOWN";
        }
    }

    public static void readExcel(String excelFilePath) {
        try (FileInputStream fis = new FileInputStream(excelFilePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            // 获取第一个工作表
            Sheet sheet = workbook.getSheetAt(0);

            // 遍历每一行
            for (Row row : sheet) {
                // 遍历每个单元格
                for (Cell cell : row) {
                    switch (cell.getCellType()) {
                        case STRING:
                            System.out.print(cell.getStringCellValue() + "\t");
                            break;
                        case NUMERIC:
                            if (DateUtil.isCellDateFormatted(cell)) {
                                System.out.print(cell.getDateCellValue() + "\t");
                            } else {
                                System.out.print(cell.getNumericCellValue() + "\t");
                            }
                            break;
                        case BOOLEAN:
                            System.out.print(cell.getBooleanCellValue() + "\t");
                            break;
                        case FORMULA:
                            System.out.print(cell.getCellFormula() + "\t");
                            break;
                        default:
                            System.out.print("UNKNOWN\t");
                            break;
                    }
                }
                System.out.println();
            }
        } catch (IOException e) {
            //e.printStackTrace();
            log.error(e.getMessage(), e);
        }
    }

}
