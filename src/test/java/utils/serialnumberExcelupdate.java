package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.Objects;

public class serialnumberExcelupdate {

    public static void writeTestResult(String serialNumber, String node, String status) {
        String excelPath = "./output/Innomate_Testcases.xlsx";

        System.out.println("Excel write started → " + serialNumber + " | " + node + " | " + status);

        Workbook workbook = null;
        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            File file = new File(excelPath);
            if (!file.exists()) {
                System.err.println("Excel file not found: " + file.getAbsolutePath());
                return;
            }

            fis = new FileInputStream(file);
            workbook = new XSSFWorkbook(fis);

            Sheet sheet = workbook.getSheet("Production_Sanity");
            if (sheet == null) {
                System.err.println("Sheet 'Production_Sanity' not found!");
                return;
            }

            // Header is in row 1 (0-indexed) → data starts from row 2 (index 2)
            int headerRowNum = 0;
            Row headerRow = sheet.getRow(headerRowNum);
            if (headerRow == null) {
                System.err.println("Header row not found!");
                return;
            }

            // Find column indices (more flexible matching)
            int serialCol = -1, nodeCol = -1, statusCol = -1;
            for (Cell cell : headerRow) {
                if (cell == null || cell.getCellType() != CellType.STRING) continue;
                String val = cell.getStringCellValue().trim().toLowerCase();
                if (val.contains("serialnumber")) {
                    serialCol = cell.getColumnIndex();
                } else if (val.contains("node")) {
                    nodeCol = cell.getColumnIndex();
                } else if (val.contains("status")) {
                    statusCol = cell.getColumnIndex();
                } else if (val.contains("Temp_Status")) {
                	statusCol = cell.getColumnIndex();
                }
            }

            if (serialCol == -1 || nodeCol == -1 || statusCol == -1) {
                System.err.println("Missing required columns. Found serial=" + serialCol +
                                   ", node=" + nodeCol + ", status=" + statusCol);
                return;
            }

            // Start checking from the first possible data row (usually row 2 = index 2)
            int startDataRow = headerRowNum + 1;  // e.g. 2
            int targetRowNum = -1;

            // Find the first row where ALL three target cells are empty or blank
            for (int r = startDataRow; r <= sheet.getLastRowNum() + 50; r++) {  // +50 as safety buffer
                Row row = sheet.getRow(r);
                if (row == null) {
                    // Completely missing row → perfect, use it
                    targetRowNum = r;
                    break;
                }

                Cell sCell = row.getCell(serialCol, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                Cell nCell = row.getCell(nodeCol, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                Cell stCell = row.getCell(statusCol, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

                boolean isEmpty = isCellBlank(sCell) && isCellBlank(nCell) && isCellBlank(stCell);

                if (isEmpty) {
                    targetRowNum = r;
                    break;
                }
            }

            if (targetRowNum == -1) {
                // Fallback: append at end if no empty slot found (rare)
                targetRowNum = sheet.getLastRowNum() + 1;
                System.out.println("No empty slot found → appending at end (row " + (targetRowNum + 1) + ")");
            } else {
                System.out.println("Writing to existing empty row: " + (targetRowNum + 1));
            }

            // Create/get the row
            Row targetRow = sheet.getRow(targetRowNum);
            if (targetRow == null) {
                targetRow = sheet.createRow(targetRowNum);
            }

            // Write values
            targetRow.createCell(serialCol).setCellValue(serialNumber);
            targetRow.createCell(nodeCol).setCellValue(node);
            targetRow.createCell(statusCol).setCellValue(status);

            // Save
            fos = new FileOutputStream(excelPath);
            workbook.write(fos);
            System.out.println("Successfully written to row " + (targetRowNum + 1));

        } catch (Exception e) {
            System.err.println("Excel write error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) fos.close();
                if (fis != null) fis.close();
                if (workbook != null) workbook.close();
            } catch (IOException ignored) {}
        }
    }

    // Helper: consider cell empty if null, blank string, or blank type
    private static boolean isCellBlank(Cell cell) {
        if (cell == null) return true;
        if (cell.getCellType() == CellType.BLANK) return true;
        if (cell.getCellType() == CellType.STRING && cell.getStringCellValue().trim().isEmpty()) return true;
        return false;
    }
}