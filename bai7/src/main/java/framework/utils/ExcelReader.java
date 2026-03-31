package framework.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {

    public static String getCellValue(Cell cell, Workbook workbook) {
        if (cell == null) {
            return "";
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();
            case NUMERIC:
                double value = cell.getNumericCellValue();
                if (value == (long) value) {
                    return String.valueOf((long) value);
                }
                return String.valueOf(value);
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                CellValue evaluated = evaluator.evaluate(cell);
                switch (evaluated.getCellType()) {
                    case STRING:
                        return evaluated.getStringValue().trim();
                    case NUMERIC:
                        double num = evaluated.getNumberValue();
                        if (num == (long) num) {
                            return String.valueOf((long) num);
                        }
                        return String.valueOf(num);
                    case BOOLEAN:
                        return String.valueOf(evaluated.getBooleanValue());
                    default:
                        return "";
                }
            default:
                return "";
        }
    }

    public static Object[][] readLoginData(String filePath, String sheetName) {
        List<Object[]> data = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);

            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                String username = getCellValue(row.getCell(0), workbook);
                String password = getCellValue(row.getCell(1), workbook);
                String expected = getCellValue(row.getCell(2), workbook);
                String description = getCellValue(row.getCell(3), workbook);

                data.add(new Object[]{username, password, expected, description});
            }

        } catch (Exception e) {
            throw new RuntimeException("Lỗi đọc Excel", e);
        }

        return data.toArray(new Object[0][]);
    }
}