package org.gen.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.gen.config.mybatis.MyBatisUtils;
import org.gen.config.mybatis.entity.TableDetail;
import org.gen.config.mybatis.mapper.ConstructQueryMapper;
import org.gen.config.mybatis.mapper.DDLMapper;
import org.gen.config.mybatis.mapper.TableDetailMapper;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DynamicGeneratorService {

    public String getByLimit(String tableName, int limit) {

        String toJson = "";
        MyBatisUtils myBatisUtils = new MyBatisUtils();
        try {
            StringBuilder query = new StringBuilder();
            query.append("SELECT * FROM ").append(tableName).append(" LIMIT ").append(limit);
            String selectQuery = query.toString();

            SqlSessionFactory sqlSessionFactory = myBatisUtils.createFactory();
            ConstructQueryMapper constructQueryMapper = myBatisUtils.createMapper(ConstructQueryMapper.class, sqlSessionFactory);

            List<Map<String, Object>> listDatas = constructQueryMapper.getByLimitDynamic(selectQuery);

            //convert key from snakcase to camelcase
            //using lambda
            List<Map<String, Object>> listCamelCaseDatas = listDatas.stream()
                    .map(originalMap -> convertSnakeToCamel(originalMap))
                    .collect(Collectors.toList());

            ObjectMapper objectMapper = new ObjectMapper();
            toJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(listCamelCaseDatas);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            myBatisUtils.closeSession();
        }
        return toJson;
    }

    public List<String> getColumnName(String tableName) {
        MyBatisUtils myBatisUtils = new MyBatisUtils();
        List<String> listColumnName = new ArrayList<>();
        try {
            SqlSessionFactory sqlSessionFactory = myBatisUtils.createFactory();
            TableDetailMapper tableDetailMapper = myBatisUtils.createMapper(TableDetailMapper.class, sqlSessionFactory);
            List<TableDetail> listTableDetail = tableDetailMapper.getTableDetail(tableName);
            for (TableDetail tableDetail : listTableDetail) {
                listColumnName.add(convertSnakeToCamel(tableDetail.getField()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            myBatisUtils.closeSession();
        }
        return listColumnName;
    }

    public ResponseEntity<FileSystemResource> getCsvFile(String tableName, String filePath, int limit) {
        MyBatisUtils myBatisUtils = new MyBatisUtils();
        List<String> listColumnName = new ArrayList<>();
        filePath = filePath + ".csv";
        try {
            SqlSessionFactory sqlSessionFactory = myBatisUtils.createFactory();

            //get column name data
            TableDetailMapper tableDetailMapper = myBatisUtils.createMapper(TableDetailMapper.class, sqlSessionFactory);
            List<TableDetail> listTableDetail = tableDetailMapper.getTableDetail(tableName);
            for (TableDetail tableDetail : listTableDetail) {
                listColumnName.add(tableDetail.getField());
            }

            //construct query for call databae
            StringBuilder query = new StringBuilder();
            query.append("SELECT * FROM ").append(tableName).append(" LIMIT ").append(limit);
            String selectQuery = query.toString();

            //get data
            ConstructQueryMapper constructQueryMapper = myBatisUtils.createMapper(ConstructQueryMapper.class, sqlSessionFactory);
            List<Map<String, Object>> listDatas = constructQueryMapper.getByLimitDynamic(selectQuery);

            //generateCSV
            exportToCsv(listDatas, listColumnName, filePath);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            myBatisUtils.closeSession();
        }
        //returnCSV
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

    private void exportToCsv(List<Map<String, Object>> listDatas, List<String> listColumnName, String filePath) {
        try (FileWriter fileWriter = new FileWriter(filePath);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {

            StringBuilder fieldNameToWrite = new StringBuilder();
            for (String fieldName : listColumnName) {
                fieldName = convertCamelToUpperSnake(fieldName);
                fieldNameToWrite.append(fieldName).append(",");
            }

            printWriter.println(fieldNameToWrite.toString());

            int index = 1;
            for (Map<String, Object> datas : listDatas) {
                StringBuilder dataToWrite = new StringBuilder();
                for (String fieldName : listColumnName) {
                    String data = datas.get(fieldName).toString();
                    dataToWrite.append(data != null ? data.toString() : "").append(",");
                }

                // Remove the trailing comma
                if (index == listDatas.size()) {
                    dataToWrite.setLength(dataToWrite.length() - 1); // Remove the last comma
                }

                // Print the formatted output
                printWriter.printf("%s%n", dataToWrite.toString()); // Adjust getters accordingly
                index++;
            }
            System.out.println("CSV file created successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> getAllTableName() {
        List<String> listTableName = new ArrayList<>();
        MyBatisUtils myBatisUtils = new MyBatisUtils();
        try {
            SqlSessionFactory sqlSessionFactory = myBatisUtils.createFactory();
            DDLMapper ddlMapper = myBatisUtils.createMapper(DDLMapper.class, sqlSessionFactory);
            listTableName = ddlMapper.getAllTableName();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            myBatisUtils.commitSession();
            myBatisUtils.closeSession();
        }
        return listTableName;
    }

    private String convertCamelToUpperSnake(String camelCase) {
        StringBuilder snakeCase = new StringBuilder();
        for (char c : camelCase.toCharArray()) {
            if (Character.isUpperCase(c)) {
                snakeCase.append('_');
                snakeCase.append(c);
            } else {
                snakeCase.append(Character.toUpperCase(c));
            }
        }
        return snakeCase.toString();
    }

    private Map<String, Object> convertSnakeToCamel(Map<String, Object> originalMap) {
        Map<String, Object> newMap = new HashMap<>();
        for (Map.Entry<String, Object> entry : originalMap.entrySet()) {
            String camelCaseKey = convertSnakeToCamel(entry.getKey());
            newMap.put(camelCaseKey, entry.getValue());
        }
        return newMap;
    }

    private String convertSnakeToCamel(String snakeCase){
        StringBuilder camelCase = new StringBuilder();
        boolean isUpper = false;
        for(char c : snakeCase.toCharArray()){
            if(c == '_'){
                isUpper = true;
            }else{
                if(isUpper){
                    camelCase.append(Character.toUpperCase(c));
                    isUpper = false;
                }else{
                    camelCase.append(c);
                }
            }
        }
        return camelCase.toString();
    }
}
