package com.preschool.demo.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class ExcelUtil {

    public static Cell createCell(Row row, Map<String, CellStyle> styles, String content, String style, int order) {
        Cell cell = row.createCell(order);
        cell.setCellValue(content);
        cell.setCellStyle(styles.get(style));
        return cell;
    }

    public static Cell createCell(Row row, String content, int order) {
        Cell cell = row.createCell(order);
        cell.setCellValue(content);
        return cell;
    }

    public static Font createFont(Workbook workbook, short fontHeight, String fontName, short color) {
        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) 10);
        font.setFontName("Arial");
        font.setColor(color);
        return font;
    }

    public static String getCellStringValue(Cell cell) {

        return readCellContent(cell);
    }

/*    public static List<List<String>> workbookToList(Workbook workbook, int columnSize) {
        DataFormatter fmt = new DataFormatter();
        Sheet sheet = workbook.getSheetAt(0);
        List<List<String>> rowValues = Lists.newArrayList();
        for (Row row : sheet) {
            List<String> cellValues = Lists.newArrayList();
            for (int j = 0; j < columnSize; j++) {
                Cell cell = row.getCell(j);
                String value = fmt.formatCellValue(cell);
                cellValues.add(value);
            }
            rowValues.add(cellValues);
        }
        return rowValues;
    }*/

    public static byte[] workbookToByteArray(Workbook workbook) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            workbook.write(bos);
        } finally {
            bos.close();
        }
        return bos.toByteArray();
    }

    public static void createCell(Map<String, CellStyle> styles, String data, Row row, int columnIndex, String normal) {
        Cell cell = row.createCell(columnIndex);
        cell.setCellStyle(styles.get(normal));
        cell.setCellValue(StringUtils.isNotBlank(data) ? data : "");
    }

    public static int createHeaderCell(Map<String, CellStyle> styles, Row headerRow, int baslikSayisi, String header, String value) {
        Cell headerCell = headerRow.createCell(baslikSayisi++);
        headerCell.setCellStyle(styles.get(header));
        headerCell.setCellValue(value);
        return baslikSayisi;
    }

    public static String readCellContent(Cell cell) {
        String content = StringUtils.EMPTY;
        if (cell != null) {
            switch (cell.getCellType()) {
                case STRING:
                    content = cell.getStringCellValue();
                    break;
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        content = cell.getDateCellValue() + StringUtils.EMPTY;
                    } else {
                        content = cell.getNumericCellValue() + StringUtils.EMPTY;
                        if (content.endsWith(".0") || content.endsWith(",0")) {
                            content = StringUtils.replace(content, ".0", StringUtils.EMPTY);
                            content = StringUtils.replace(content, ",0", StringUtils.EMPTY);
                        }
                    }
                    break;
                case BOOLEAN:
                    content = cell.getBooleanCellValue() + StringUtils.EMPTY;
                    break;
                case FORMULA:
                    content = cell.getCellFormula() + StringUtils.EMPTY;
                    break;
                default:
                    content = StringUtils.EMPTY;
            }
        }
        return content;
    }

    public static String formatDate(ZonedDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        return formatter.format(date);
    }

    public static String formatDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return formatter.format(date);
    }
}
