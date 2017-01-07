package com.analytics.excel;

import org.apache.poi.ss.usermodel.CellStyle;

public class ConfigExcel {
    public static CellStyle STYLE_DESCRIPTION = CreateExcelReport.book.createCellStyle();
    public static CellStyle STYLE_DESCRIPTION_CENTER = CreateExcelReport.book.createCellStyle();
    public static CellStyle STYLE_FUNNEL = CreateExcelReport.book.createCellStyle();
    public static CellStyle STYLE_SEARCH_CONTEXT_SIMPLE = CreateExcelReport.book.createCellStyle();
    public static CellStyle STYLE_SEARCH_CONTEXT = CreateExcelReport.book.createCellStyle();
    public static CellStyle STYLE_DEVICE = CreateExcelReport.book.createCellStyle();
    public static CellStyle STYLE_DEVICE_SIMPLE = CreateExcelReport.book.createCellStyle();
    public static CellStyle STYLE_COST_NO_CONVERSTION = CreateExcelReport.book.createCellStyle();
    public static CellStyle STYLE_COST_NO_CONVERSTION_1 = CreateExcelReport.book.createCellStyle();
    public static CellStyle STYLE_COST_EXPENSIVE_CONVERSATION = CreateExcelReport.book.createCellStyle();
    public static CellStyle STYLE_COST_EXPENSIVE_CONVERSATION_1 = CreateExcelReport.book.createCellStyle();
    public static CellStyle STYLE_COST_CHEAP_CONVERSATION = CreateExcelReport.book.createCellStyle();
    public static CellStyle STYLE_COST_CHEAP_CONVERSATION_1 = CreateExcelReport.book.createCellStyle();
    public static CellStyle STYLE_DATE = CreateExcelReport.book.createCellStyle();
    public static CellStyle STYLE_ADVERT = CreateExcelReport.book.createCellStyle();

    static{
        STYLE_DESCRIPTION = CreateExcelReport.sheetStyle.getRow(5).getCell(0).getCellStyle();
        STYLE_FUNNEL = CreateExcelReport.sheetStyle.getRow(2).getCell(2).getCellStyle();
        STYLE_SEARCH_CONTEXT = CreateExcelReport.sheetStyle.getRow(3).getCell(1).getCellStyle();
        STYLE_SEARCH_CONTEXT_SIMPLE = CreateExcelReport.sheetStyle.getRow(4).getCell(1).getCellStyle();
        STYLE_DEVICE = CreateExcelReport.sheetStyle.getRow(3).getCell(2).getCellStyle();
        STYLE_DEVICE_SIMPLE = CreateExcelReport.sheetStyle.getRow(4).getCell(2).getCellStyle();
        STYLE_DESCRIPTION_CENTER = CreateExcelReport.sheetStyle.getRow(5).getCell(1).getCellStyle();

        STYLE_COST_NO_CONVERSTION = CreateExcelReport.sheetStyle.getRow(6).getCell(0).getCellStyle();
        STYLE_COST_NO_CONVERSTION.setWrapText(true);
        STYLE_COST_NO_CONVERSTION_1 = CreateExcelReport.sheetStyle.getRow(7).getCell(0).getCellStyle();
        STYLE_COST_NO_CONVERSTION_1.setWrapText(true);
        STYLE_COST_EXPENSIVE_CONVERSATION = CreateExcelReport.sheetStyle.getRow(8).getCell(0).getCellStyle();
        STYLE_COST_EXPENSIVE_CONVERSATION.setWrapText(true);
        STYLE_COST_EXPENSIVE_CONVERSATION_1 = CreateExcelReport.sheetStyle.getRow(9).getCell(0).getCellStyle();
        STYLE_COST_EXPENSIVE_CONVERSATION_1.setWrapText(true);
        STYLE_COST_CHEAP_CONVERSATION = CreateExcelReport.sheetStyle.getRow(10).getCell(0).getCellStyle();
        STYLE_COST_CHEAP_CONVERSATION.setWrapText(true);
        STYLE_COST_CHEAP_CONVERSATION_1 = CreateExcelReport.sheetStyle.getRow(11).getCell(0).getCellStyle();
        STYLE_COST_CHEAP_CONVERSATION_1.setWrapText(true);
        STYLE_DATE = CreateExcelReport.sheetStyle.getRow(14).getCell(0).getCellStyle();
        STYLE_ADVERT = CreateExcelReport.sheetStyle.getRow(0).getCell(1).getCellStyle();
    }
}
