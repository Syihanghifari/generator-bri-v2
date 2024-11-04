package org.gen.service;

import org.apache.ibatis.session.SqlSessionFactory;
import org.gen.config.mybatis.MyBatisUtils;
import org.gen.config.mybatis.entity.Finance;
import org.gen.config.mybatis.entity.TableColumnDetail;
import org.gen.config.mybatis.entity.TableDetail;
import org.gen.config.mybatis.mapper.FinanceMapper;
import org.gen.config.mybatis.mapper.TableDetailMapper;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Service
public class FinanceService {

    public List<Finance> getByLimit(int limit){
        MyBatisUtils myBatisUtils = new MyBatisUtils();
        List<Finance> listFinance = new ArrayList<>();
        try{
            SqlSessionFactory sqlSessionFactory = myBatisUtils.createFactory();
            FinanceMapper financeMapper = myBatisUtils.createMapper(FinanceMapper.class, sqlSessionFactory);
            listFinance = financeMapper.getByLimit(limit);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            myBatisUtils.closeSession();
        }
        return listFinance;
    }

    public List<String> getColumnName(){
        MyBatisUtils myBatisUtils = new MyBatisUtils();
        List<TableDetail> listTableDetail = new ArrayList<>();
        List<String> listColumnName = new ArrayList<>();
        try{
            SqlSessionFactory sqlSessionFactory = myBatisUtils.createFactory();
            TableDetailMapper tableDetailMapper = myBatisUtils.createMapper(TableDetailMapper.class,sqlSessionFactory);
            listTableDetail = tableDetailMapper.getTableDetail("finance");
            for(TableDetail tableDetail : listTableDetail){
                    listColumnName.add(tableDetail.getField());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            myBatisUtils.closeSession();
        }
        return listColumnName;
    }

    public List<Finance> getAll(){
        MyBatisUtils myBatisUtils = new MyBatisUtils();
        List<Finance> listFinance = new ArrayList<>();
        try{
            SqlSessionFactory sqlSessionFactory = myBatisUtils.createFactory();
            FinanceMapper financeMapper = myBatisUtils.createMapper(FinanceMapper.class, sqlSessionFactory);
            listFinance = financeMapper.getAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            myBatisUtils.closeSession();
        }
        return listFinance;
    }

    public void getCsvFile(String filePath,int limit){
        MyBatisUtils myBatisUtils = new MyBatisUtils();
        List<Finance> listFinance = new ArrayList<>();
        try{
            SqlSessionFactory sqlSessionFactory = myBatisUtils.createFactory();
            FinanceMapper financeMapper = myBatisUtils.createMapper(FinanceMapper.class, sqlSessionFactory);
            listFinance = financeMapper.getByLimit(limit);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            myBatisUtils.closeSession();
        }
        exportToCsv(listFinance,filePath);
    }

    private void exportToCsv(List<Finance> listFinance, String filePath) {
        try (FileWriter fileWriter = new FileWriter(filePath);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {

            // Write the header
            Finance table = new Finance();
            List<String> listFieldName = getClassFieldName(table);
            StringBuilder fieldNameToWrite = new StringBuilder();
            for(String fieldName : listFieldName){
                fieldName = convertCamelToUpperSnake(fieldName);
                fieldNameToWrite.append(fieldName).append(",");
            }

            // Remove the last comma from the header
//            if (fieldNameToWrite.length() > 0) {
//                fieldNameToWrite.setLength(fieldNameToWrite.length() - 1); // Remove the last comma
//            }

            printWriter.println(fieldNameToWrite.toString()); // Adjust column names accordingly

            // Write the data
            Class<?> financeClass = Finance.class;
            int index = 1;
            for (Finance finance : listFinance) {
                StringBuilder dataToWrite = new StringBuilder();
                for (String fieldName : listFieldName) {
                    // Assuming getters follow the "getFieldName" convention
                    Method getDataMethod = financeClass.getDeclaredMethod("get" + capitalizeFirstLetter(fieldName));
                    Object data = getDataMethod.invoke(finance);

                    // Append the data, handling null values gracefully
                    dataToWrite.append(data != null ? data.toString() : "").append(",");
                }

                // Remove the trailing comma
                if (index == listFinance.size()) {
                    dataToWrite.setLength(dataToWrite.length() - 1); // Remove the last comma
                }

                // Print the formatted output
                printWriter.printf("%s%n", dataToWrite.toString()); // Adjust getters accordingly
                index++;
            }

            System.out.println("CSV file created successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void getCsvFileV2(List<String> listReqData,String filePath,int limit){
        //split into 2 list
        List<String> listFieldName = new ArrayList<>();
        List<String> listColumnName = new ArrayList<>();

        for(String datas : listReqData){
            String[] data =  datas.split("\\|\\|");
            String fieldName = data[0];
            String columnName = data[1];
            listFieldName.add(fieldName);
            listColumnName.add(columnName);
        }

        //compress listColumnName to remove duplicate name
        List<String> listColumnNameDb = new ArrayList<>();
        for(String columnName: listColumnName){
            if(!listColumnNameDb.contains(columnName))
                listColumnNameDb.add(columnName);
        }
        //build query
        StringBuilder query = new StringBuilder();
        query.append("SELECT ");
        for(String columnName : listColumnNameDb){
            query.append(columnName).append(",");
        }
        query.setLength(query.length() - 1);
        query.append(" FROM finance limit ").append(limit);

        MyBatisUtils myBatisUtils = new MyBatisUtils();
        List<Finance> listFinance = new ArrayList<>();
        try{
            SqlSessionFactory sqlSessionFactory = myBatisUtils.createFactory();
            FinanceMapper financeMapper = myBatisUtils.createMapper(FinanceMapper.class, sqlSessionFactory);
            listFinance = financeMapper.getByConstructQuery(query.toString());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            myBatisUtils.closeSession();
        }
        exportToCsvV2(listFinance,listFieldName,listColumnName,filePath);
    }

    public void getSqlFileCreateTable(String filePath,String tableName){
        MyBatisUtils myBatisUtils = new MyBatisUtils();
        List<TableColumnDetail> listTableColumnDetail = new ArrayList<>();
        try{
            SqlSessionFactory sqlSessionFactory = myBatisUtils.createFactory();
            TableDetailMapper tableDetailMapper = myBatisUtils.createMapper(TableDetailMapper.class, sqlSessionFactory);
            listTableColumnDetail = tableDetailMapper.getTableColumnDetail(tableName);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            myBatisUtils.closeSession();
        }
        exportToSqlCreateTable(listTableColumnDetail,filePath,tableName);
    }

    private void exportToSqlCreateTable(List<TableColumnDetail> listTableColumnDetail, String filePath, String tableName){
        try (FileWriter fileWriter = new FileWriter(filePath);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {

            StringBuilder dataToWrite = new StringBuilder();

            dataToWrite.append("CREATE TABLE " + tableName + " ( ");

            int checkpoint = dataToWrite.length();
            int index = 0;

            for(TableColumnDetail tableColumnDetail : listTableColumnDetail){
                if(index > 0){
                    for(int i = 0 ; i < checkpoint ; i++){
                        dataToWrite.append(" ");
                    }
                }
                dataToWrite.append(tableColumnDetail.getColumnName()).append(" ")
                        .append(tableColumnDetail.getColumnType()).append(",").append(System.lineSeparator());
                index++;
            }

            if(dataToWrite.length() > 0)
                dataToWrite.setLength(dataToWrite.length() - 3);

            dataToWrite.append(" );");
            printWriter.println(dataToWrite.toString());
            System.out.println("SQL file created successfully!");
        }catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void exportToCsvV2(List<Finance> listFinance, List<String> listFieldName,List<String> listColumnName, String filePath) {
        try (FileWriter fileWriter = new FileWriter(filePath);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {

            // Write the header
            StringBuilder fieldNameToWrite = new StringBuilder();
            for(String fieldName : listFieldName){
                fieldNameToWrite.append(fieldName.toUpperCase()).append(",");
            }

            printWriter.println(fieldNameToWrite.toString()); // Adjust column names accordingly

            // Write the data
            Class<?> financeClass = Finance.class;
            int index = 1;
            for (Finance finance : listFinance) {
                StringBuilder dataToWrite = new StringBuilder();
                for (String fieldName : listColumnName) {
                    // Assuming getters follow the "getFieldName" convention
                    Method getDataMethod = financeClass.getDeclaredMethod("get" + capitalizeFirstLetter(fieldName));
                    Object data = getDataMethod.invoke(finance);

                    // Append the data, handling null values gracefully
                    dataToWrite.append(data != null ? data.toString() : "").append(",");
                }

                // Remove the trailing comma
                if (index == listFinance.size()) {
                    dataToWrite.setLength(dataToWrite.length() - 1); // Remove the last comma
                }

                // Print the formatted output
                printWriter.printf("%s%n", dataToWrite.toString()); // Adjust getters accordingly
                index++;
            }

            System.out.println("CSV file created successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static List<String> getClassFieldName(Object obj) {
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        List<String> listClassFieldName = new ArrayList<>();
        for (Field field : fields) {
            field.setAccessible(true);
            listClassFieldName.add(field.getName());
        }
        return listClassFieldName;
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

    private String capitalizeFirstLetter(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }

}
