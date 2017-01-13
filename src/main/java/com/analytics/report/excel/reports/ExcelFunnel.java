package com.analytics.report.excel.reports;

import com.analytics.entity.client.QueryClient;
import com.analytics.report.dao.FunnelDAO;
import com.analytics.report.entity.report.Funnel;
import com.analytics.report.excel.StyleExcel;
import com.analytics.report.excel.CreateExcelReport;
import org.apache.poi.hssf.util.AreaReference;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.text.DecimalFormat;

public class ExcelFunnel implements FillingExcel {
    private Funnel funnel;
    public static String QUALITY_SHOW_ADVERT = "qualityShowAdvert";
    public static String QUALITY_CLICK = "qualityClick";
    public static String SEND_ALL = "sendAll";
    public static String COUNT_GET_GOALS = "countGetGoals";
    public static String RATIO_CONVERSATION = "effecientConversation";
    public static String CPA_COST = "CpaCost";
    public static double costOneConversation;
    private ExcelRecommendation excelRecommendation;


    public ExcelFunnel(QueryClient queryClient, ExcelRecommendation excelRecommendation) {
        funnel = new FunnelDAO().getFunnel(queryClient);
        this.excelRecommendation = excelRecommendation;
        DecimalFormat decimalFormat = new DecimalFormat("##0.000");
        String ratioConversation = decimalFormat.format(funnel.getEffecientConversation());
        DecimalFormat decimalFormat1 = new DecimalFormat("##0.00");
        String cpaCost = decimalFormat1.format(funnel.getCpaCost());
        String spendALL = decimalFormat1.format(funnel.getSpendAll());
        costOneConversation = funnel.getSpendAll() / funnel.getQualityGetGoals();

        changeCellFromRange(QUALITY_SHOW_ADVERT, String.valueOf(funnel.getQualityShowAdvert()));
        changeCellFromRange(QUALITY_CLICK, String.valueOf(funnel.getQualityClick()));
        changeCellFromRange(SEND_ALL, spendALL);
        changeCellFromRange(COUNT_GET_GOALS, String.valueOf(funnel.getQualityGetGoals()));
        changeCellFromRange(RATIO_CONVERSATION, String.valueOf(ratioConversation));
        changeCellFromRange(CPA_COST, cpaCost);

    }

    @Override
    public void fillListToExcel(XSSFSheet sheet) {

    }

    @Override
    public void changeRange(int start, int end, String column, String rangeName) {

    }

    @Override
    public void changeCellFromRange(String rangeName, String changeValue) {
        int namedCellIdx = CreateExcelReport.book.getNameIndex(rangeName);
        Name aNamedCell = CreateExcelReport.book.getNameAt(namedCellIdx);
        AreaReference aref = new AreaReference(aNamedCell.getRefersToFormula());
        CellReference[] cells = aref.getAllReferencedCells();
        Cell c = null;
        Sheet s = CreateExcelReport.book.getSheet(cells[0].getSheetName());
        Row r =  s.getRow(cells[0].getRow());
        c = r.getCell(cells[0].getCol());
        c.setCellValue(changeValue);
        c.setCellStyle(StyleExcel.STYLE_FUNNEL);

    }
}