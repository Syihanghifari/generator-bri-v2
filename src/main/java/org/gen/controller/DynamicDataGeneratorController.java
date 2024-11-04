package org.gen.controller;

import org.gen.config.mybatis.entity.Customers;
import org.gen.service.DynamicGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/dyta")
@CrossOrigin(origins = "*")
public class DynamicDataGeneratorController {

    @Autowired
    DynamicGeneratorService dynamicGeneratorService;

    //http://localhost:2972/dyta/getByLimit
    @GetMapping(path = "/getByLimit")
    public String getByLimit(@RequestHeader String tableName,@RequestHeader int limit){
        return dynamicGeneratorService.getByLimit(tableName,limit);
    }

    //http://localhost:2972/dyta/getColumnName
    @GetMapping(path = "/getColumnName")
    public List<String> getColumnName(@RequestHeader String tableName){
        return dynamicGeneratorService.getColumnName(tableName);
    }

    //http://localhost:2972/dyta/getCsv
    @GetMapping(path = "/getCsv")
    public ResponseEntity<FileSystemResource> getCsv(@RequestHeader String tableName,@RequestHeader String fileName, @RequestHeader int limit){
        return dynamicGeneratorService.getCsvFile(tableName,fileName,limit);
    }

        @GetMapping(path = "/getAllTableName")
    public List<String> getAllTableName(){
        return dynamicGeneratorService.getAllTableName();
    }
}
