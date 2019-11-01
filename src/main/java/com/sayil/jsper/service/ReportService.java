package com.sayil.jsper.service;

import com.sayil.jsper.entity.Customer;
import com.sayil.jsper.repo.CustomerRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    public static final String filePath = "C:\\Users\\Asus\\Documents\\reservations";
    @Autowired
    CustomerRepository customerRepository;
    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
        List<Customer> customers = customerRepository.findAll();
        //Load file and compile
        File file = ResourceUtils.getFile("classpath:customer.jrxml");
        JasperReport jasperReport;
        jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(customers);
        Map<String, Object> map= new HashMap<>();
        map.put("created by","Sayil LLC");
        JasperPrint print = JasperFillManager.fillReport(jasperReport,map,dataSource);
        if(reportFormat.equalsIgnoreCase("html")){
            JasperExportManager.exportReportToHtmlFile
                    (print, filePath + "\\customers.html");

        }else if(reportFormat.equalsIgnoreCase("pdf"))
        {
            JasperExportManager.exportReportToPdfFile
                    (print,filePath + "\\customers.pdf");
        }
        return "reported generated successfully in " + filePath;
    }

}
