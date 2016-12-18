package com.analytics.excel;

import org.apache.poi.ss.usermodel.CellStyle;

public class ConfigExcel {
    public static CellStyle STYLE_DESCRIPTION = CreateExcelReport.book.createCellStyle();
    public static CellStyle STYLE_FUNNEL = CreateExcelReport.book.createCellStyle();
    public static CellStyle STYLE_SEARCH_CONTEXT_SIMPLE = CreateExcelReport.book.createCellStyle();
    public static CellStyle STYLE_SEARCH_CONTEXT = CreateExcelReport.book.createCellStyle();
    public static CellStyle STYLE_DEVICE = CreateExcelReport.book.createCellStyle();
    public static CellStyle STYLE_DEVICE_SIMPLE = CreateExcelReport.book.createCellStyle();

    static{
        STYLE_DESCRIPTION = CreateExcelReport.sheetStyle.getRow(5).getCell(0).getCellStyle();
        STYLE_FUNNEL = CreateExcelReport.sheetStyle.getRow(2).getCell(2).getCellStyle();
        STYLE_SEARCH_CONTEXT = CreateExcelReport.sheetStyle.getRow(3).getCell(1).getCellStyle();
        STYLE_SEARCH_CONTEXT_SIMPLE = CreateExcelReport.sheetStyle.getRow(4).getCell(1).getCellStyle();
        STYLE_DEVICE = CreateExcelReport.sheetStyle.getRow(3).getCell(2).getCellStyle();
        STYLE_DEVICE_SIMPLE = CreateExcelReport.sheetStyle.getRow(4).getCell(2).getCellStyle();
    }
}
