package edu.azati.shop.services;

import edu.azati.shop.entity.Product;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;

@Service
public class JasperService {
    public void generatePdf(List<Product> products) throws FileNotFoundException, JRException {
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(products);
        JasperReport jasperReport = JasperCompileManager.compileReport(new FileInputStream("src/main/resources/orderReport.jrxml"));
        HashMap<String, Object> map = new HashMap<>();
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, beanCollectionDataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, "report.pdf");
    }
}
