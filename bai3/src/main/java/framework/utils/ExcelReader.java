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

        CellType cellType = cell.getCellType();

        switch (cellType) {
            case STRING:
                return cell.getStringCellValue().trim();

            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                }
                double numValue = cell.getNumericCellValue();
                if (numValue == (long) numValue) {
                    return String.valueOf((long) numValue);
                }
                return String.valueOf(numValue);

            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());

            case FORMULA:
                FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                CellValue evaluatedValue = evaluator.evaluate(cell);

                switch (evaluatedValue.getCellType()) {
                    case STRING:
                        return evaluatedValue.getStringValue().trim();
                    case NUMERIC:
                        double formulaNum = evaluatedValue.getNumberValue();
                        if (formulaNum == (long) formulaNum) {
                            return String.valueOf((long) formulaNum);
                        }
                        return String.valueOf(formulaNum);
                    case BOOLEAN:
                        return String.valueOf(evaluatedValue.getBooleanValue());
                    default:
                        return "";
                }

            case BLANK:
            default:
                return "";
        }
    }

    public static Object[][] readSheetAsDataProvider(String filePath, String sheetName, boolean successCase) {
        List<Object[]> dataList = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                return new Object[0][];
            }

            Row headerRow = sheet.getRow(0);
            if (headerRow == null) {
                return new Object[0][];
            }

            int rowCount = sheet.getPhysicalNumberOfRows();

            for (int i = 1; i < rowCount; i++) {
                Row row = sheet.getRow(i);
                if (row == null) {
                    continue;
                }

                String username = getCellValue(row.getCell(0), workbook);
                String password = getCellValue(row.getCell(1), workbook);
                String expectedValue = getCellValue(row.getCell(2), workbook);
                String description = getCellValue(row.getCell(3), workbook);

                TestData testData;
                if (successCase) {
                    testData = new TestData(username, password, expectedValue, "", description, sheetName);
                } else {
                    testData = new TestData(username, password, "", expectedValue, description, sheetName);
                }

                dataList.add(new Object[]{testData});
            }

        } catch (Exception e) {
            throw new RuntimeException("Lỗi đọc Excel sheet: " + sheetName, e);
        }

        return dataList.toArray(new Object[0][]);
    }

    public static Object[][] getSmokeCases(String filePath) {
        return readSheetAsDataProvider(filePath, "SmokeCases", true);
    }

    public static Object[][] getNegativeCases(String filePath) {
        return readSheetAsDataProvider(filePath, "NegativeCases", false);
    }

    public static Object[][] getBoundaryCases(String filePath) {
        return readSheetAsDataProvider(filePath, "BoundaryCases", false);
    }

    public static Object[][] getAllRegressionCases(String filePath) {
        List<Object[]> allData = new ArrayList<>();

        Object[][] smoke = getSmokeCases(filePath);
        Object[][] negative = getNegativeCases(filePath);
        Object[][] boundary = getBoundaryCases(filePath);

        for (Object[] row : smoke) {
            allData.add(row);
        }
        for (Object[] row : negative) {
            allData.add(row);
        }
        for (Object[] row : boundary) {
            allData.add(row);
        }

        return allData.toArray(new Object[0][]);
    }
}