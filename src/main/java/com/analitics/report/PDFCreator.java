package com.analitics.report;

import com.analitics.entity.respons.ya.data.Dimension;
import com.analitics.entity.respons.ya.data.DimensionData;
import com.analitics.entity.respons.ya.data.SourceVisited;
import com.analitics.entity.respons.ya.data.SourceVisitedFromYa;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import java.io.File;
import java.io.IOException;
import java.util.*;


public class PDFCreator {

    String pathForSaving = "D:\\Reports.pdf";
    String pathForPattern = "src\\pattern\\reports.jrxml";

    public PDFCreator(SourceVisitedFromYa sourceVisitedFromYa) throws IOException {
        ArrayList<SourceVisited> list = new ArrayList<>();
        for (DimensionData data : sourceVisitedFromYa.getData()) {
            for (Dimension dimension : data.getDimensions()) {
                for (List<Double> value : data.getMetrics()) {
                    Double summ = 0.0;
                    for (Double eacheValue : value) {
                        summ += eacheValue;
                    }
                    list.add(new SourceVisited(dimension.getName(), summ));
                }
            }
        }

        Map<String, Object> parametrs = new HashMap<>();
        parametrs.put("DATE", new Date());
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(list);
        File pattern = new File(pathForPattern);
        JasperDesign jasperDesign = null;
        try {
            jasperDesign = JRXmlLoader.load(pattern);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametrs, beanCollectionDataSource);
            JasperExportManager.exportReportToHtmlFile(jasperPrint, pathForSaving);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }
}
