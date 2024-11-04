package org.gen.controller;

import org.gen.config.mybatis.entity.Customers;
import org.gen.config.mybatis.entity.Finance;
import org.gen.service.CustomersService;
import org.gen.service.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping(value = "/data")
@CrossOrigin(origins = "*")
public class DataGeneratorController {

    @Autowired
    CustomersService customersService;

    @Autowired
    FinanceService financeService;


    //http://localhost:2972/data/getByLimitCustomers
    @GetMapping(path = "/getByLimitCustomers")
    public List<Customers> getByLimitCustomers(@RequestHeader int limit){
        return customersService.getByLimit(limit);
    }

    //http://localhost:2972/data/getByLimitFinance
    @GetMapping(path = "/getByLimitFinance")
    public List<Finance> getByLimitFinance(@RequestHeader int limit){
        return financeService.getByLimit(limit);
    }

    //http://localhost:2972/data/getColumnNameCustomers
    @GetMapping(path = "/getColumnNameCustomers")
    public List<String> getColumnNameCustomers(){
        return customersService.getColumnName();
    }

    //http://localhost:2972/data/getColumnNameFinance
    @GetMapping(path = "/getColumnNameFinance")
    public List<String> getColumnNameFinance(){
        return financeService.getColumnName();
    }

    //http://localhost:2972/data/getFinanceCsv
    @GetMapping(path = "/getFinanceCsv")
    public ResponseEntity<FileSystemResource> getFinanceCsv(@RequestHeader String fileName,@RequestHeader  int limit){
        String filePath = fileName + ".csv";
        financeService.getCsvFile(filePath,limit);

        File csvFile = new File(filePath);
        if (!csvFile.exists()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Handle file not found
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + csvFile.getName());
        headers.add(HttpHeaders.CONTENT_TYPE, "application/csv");
        headers.add(HttpHeaders.CONTENT_LENGTH, String.valueOf(csvFile.length()));

        return new ResponseEntity<>(new FileSystemResource(csvFile), headers, HttpStatus.OK);
    }

    //http://localhost:2972/data/getFinanceCsv/v2
    @PostMapping(path = "/getFinanceCsv/v2")
    public ResponseEntity<FileSystemResource> getFinanceCsvV2(@RequestHeader String fileName,@RequestHeader  int limit,@RequestBody List<String> listReqData){
        String filePath = fileName + ".csv";
        financeService.getCsvFileV2(listReqData,filePath,limit);

        File csvFile = new File(filePath);
        if (!csvFile.exists()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Handle file not found
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + csvFile.getName());
        headers.add(HttpHeaders.CONTENT_TYPE, "application/csv");
        headers.add(HttpHeaders.CONTENT_LENGTH, String.valueOf(csvFile.length()));

        return new ResponseEntity<>(new FileSystemResource(csvFile), headers, HttpStatus.OK);
    }

    //http://localhost:2972/data/getCustomersCsv
    @GetMapping(path = "/getCustomersCsv")
    public ResponseEntity<FileSystemResource> geCustomerstCsv(@RequestHeader String fileName,@RequestHeader int limit){
        String filePath = fileName + ".csv";
        customersService.getCsvFile(filePath,limit);

        File csvFile = new File(filePath);
        if (!csvFile.exists()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Handle file not found
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + csvFile.getName());
        headers.add(HttpHeaders.CONTENT_TYPE, "application/csv");
        headers.add(HttpHeaders.CONTENT_LENGTH, String.valueOf(csvFile.length()));

        return new ResponseEntity<>(new FileSystemResource(csvFile), headers, HttpStatus.OK);
    }

    //http://localhost:2972/data/getCustomersCsv/v2
    @PostMapping(path = "/getCustomersCsv/v2")
    public ResponseEntity<FileSystemResource> geCustomerstCsvV2(@RequestHeader String fileName,@RequestHeader int limit,@RequestBody List<String> listReqData){
        String filePath = fileName + ".csv";
        customersService.getCsvFileV2(listReqData,filePath,limit);

        File csvFile = new File(filePath);
        if (!csvFile.exists()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Handle file not found
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + csvFile.getName());
        headers.add(HttpHeaders.CONTENT_TYPE, "application/csv");
        headers.add(HttpHeaders.CONTENT_LENGTH, String.valueOf(csvFile.length()));

        return new ResponseEntity<>(new FileSystemResource(csvFile), headers, HttpStatus.OK);
    }

    //http://localhost:2972/data/customers/csv/v3
    @GetMapping(path = "/customers/csv/v3")
    public ResponseEntity<FileSystemResource> getCustomersCsvV3(@RequestHeader String fileName,@RequestHeader int limit,@RequestParam List<String> listReqData){
        String filePath = fileName + ".csv";
        customersService.getCsvFileV2(listReqData,filePath,limit);

        File csvFile = new File(filePath);
        if (!csvFile.exists()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Handle file not found
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + csvFile.getName());
        headers.add(HttpHeaders.CONTENT_TYPE, "application/csv");
        headers.add(HttpHeaders.CONTENT_LENGTH, String.valueOf(csvFile.length()));

        return new ResponseEntity<>(new FileSystemResource(csvFile), headers, HttpStatus.OK);
    }

    //http://localhost:2972/data/finance/csv/v3
    @GetMapping(path = "/finance/csv/v3")
    public ResponseEntity<FileSystemResource> getFinanceCsvV3(@RequestHeader String fileName,@RequestHeader  int limit,@RequestParam List<String> listReqData){
        String filePath = fileName + ".csv";
        financeService.getCsvFileV2(listReqData,filePath,limit);

        File csvFile = new File(filePath);
        if (!csvFile.exists()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Handle file not found
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + csvFile.getName());
        headers.add(HttpHeaders.CONTENT_TYPE, "application/csv");
        headers.add(HttpHeaders.CONTENT_LENGTH, String.valueOf(csvFile.length()));

        return new ResponseEntity<>(new FileSystemResource(csvFile), headers, HttpStatus.OK);
    }

    @GetMapping(path = "/customers/sql/v1")
    public ResponseEntity<FileSystemResource> sqlFileCustomers(@RequestParam String fileName, @RequestParam String tableName){
        String filePath = fileName + ".sql";
        customersService.getSqlFileCreateTable(filePath,tableName);

        File sqlFile = new File(filePath);
        if (!sqlFile.exists()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Handle file not found
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + sqlFile.getName());
        headers.add(HttpHeaders.CONTENT_TYPE, "application/sql");
        headers.add(HttpHeaders.CONTENT_LENGTH, String.valueOf(sqlFile.length()));

        return new ResponseEntity<>(new FileSystemResource(sqlFile), headers, HttpStatus.OK);
    }

    @GetMapping(path = "/finance/sql/v1")
    public ResponseEntity<FileSystemResource> sqlFileFinance(@RequestParam String fileName, @RequestParam String tableName){
        String filePath = fileName + ".sql";
        financeService.getSqlFileCreateTable(filePath,tableName);

        File sqlFile = new File(filePath);
        if (!sqlFile.exists()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Handle file not found
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + sqlFile.getName());
        headers.add(HttpHeaders.CONTENT_TYPE, "application/sql");
        headers.add(HttpHeaders.CONTENT_LENGTH, String.valueOf(sqlFile.length()));

        return new ResponseEntity<>(new FileSystemResource(sqlFile), headers, HttpStatus.OK);
    }
    //    @GetMapping(path = "/getAllTableName")
//    public List<String> getAllTableName(){
//        return customersService.getAllTableName();
//    }

//    //http://localhost:2972/data/createTable
//    @PutMapping(path = "/createTable")
//    public String createTable(){
//        return customersService.createTable();
//    }

}
