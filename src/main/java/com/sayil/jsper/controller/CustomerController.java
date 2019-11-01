package com.sayil.jsper.controller;

import com.sayil.jsper.entity.Customer;
import com.sayil.jsper.repo.CustomerRepository;
import com.sayil.jsper.service.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ReportService reportService;

    @GetMapping("/getAll")
    public List<Customer> getAllCustomers(){

     return   customerRepository.findAll();
    }

    @GetMapping("/report/{format}")
    public String reportFormat(@PathVariable("format") String format) throws FileNotFoundException, JRException {
      return  reportService.exportReport(format);
    }
}
