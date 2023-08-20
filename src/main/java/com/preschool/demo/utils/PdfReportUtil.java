/*
package com.preschool.demo.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class PdfReportUtil {

    private static final String FONT_NAME = "Helvetica";
    private static final String ENCODE = "CP1254";
    public static final int FONT_FORMAT = Font.NORMAL;

    private static PdfReportUtil instance;

    private PdfReportUtil() {
    }

    public static PdfReportUtil getInstance() {
        if (instance == null) {
            synchronized (PdfReportUtil.class) {
                if (instance == null) {
                    instance = new PdfReportUtil();
                }
            }
        }
        return instance;
    }

    public PdfPCell addCell(String value) {
        PdfPCell cell = new PdfPCell(new Phrase(value, turkishCharacter(Font.NORMAL)));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        return cell;
    }

    public PdfPCell addHeaderCell(String value) {
        PdfPCell cell = new PdfPCell(new Phrase(value, turkishCharacter(Font.BOLD)));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        return cell;
    }

    private Font turkishCharacter(int font) {
        BaseFont STF_Helvetica_Turkish;
        {
            try {
                STF_Helvetica_Turkish = BaseFont.createFont(FONT_NAME, ENCODE, BaseFont.NOT_EMBEDDED);
                return new Font(STF_Helvetica_Turkish, 6, font);
            } catch (DocumentException | IOException e) {
                e.printStackTrace();
            }
        }
        return FontFactory.getFont(FontFactory.HELVETICA_BOLD, FONT_FORMAT, 6);
    }

    public static PdfWriter getPrintWriter(Document document, ByteArrayOutputStream out) throws DocumentException {
        return PdfWriter.getInstance(document, out);
    }

    public PdfPTable getPdfTable(int column, int widthPercentage, int... x) throws DocumentException {
        PdfPTable table = new PdfPTable(column);
        table.setWidthPercentage(widthPercentage);
        table.setWidths(x);
        return table;
    }
}
*/
