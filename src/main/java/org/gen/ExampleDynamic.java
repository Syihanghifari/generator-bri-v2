package org.gen;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.apache.ibatis.session.SqlSessionFactory;
import org.gen.config.mybatis.MyBatisUtils;
import org.gen.config.mybatis.mapper.CustomersMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ExampleDynamic {
    private static Logger logger = Logger.getLogger("Example");

    public static void main(String[] args) {
        long startDt = System.currentTimeMillis();
        MyBatisUtils myBatisUtils = new MyBatisUtils();
        try{
            String driver = "com.mysql.cj.jdbc.Driver";
            String uri = "jdbc:mysql://20.167.40.12:3306/voltdb?allowPublicKeyRetrieval=true";
            String username = "voltage";
            String password = "Voltage123!";
            SqlSessionFactory sqlSessionFactory = myBatisUtils.createFactory(driver,uri,username,password);
            CustomersMapper customersMapper = myBatisUtils.createMapper(CustomersMapper.class, sqlSessionFactory);
            List<Map<String, Object>> listCustomers = customersMapper.getByLimitDynamic(100);

            // Convert snake_case keys to camelCase
            //Using Method Reference
            List<Map<String, Object>> camelCaseCustomers = listCustomers.stream()
                    .map(ExampleDynamic::convertSnakeToCamel)
                    .collect(Collectors.toList());

            // Convert snake_case keys to camelCase
            //using lambda
//            List<Map<String, Object>> camelCaseCustomers = listCustomers.stream()
//                    .map(originalMap -> convertSnakeToCamel(originalMap))
//                    .collect(Collectors.toList());

            //breakdown for using lambda
//            List<Map<String, Object>> listCamelCaseDatas = new ArrayList<>();
//            for(Map<String, Object> originalMap : listDatas ){
//                listCamelCaseDatas.add(convertSnakeToCamel(originalMap));
//            }

            String json = "";
            //using jakson
            ObjectMapper objectMapper = new ObjectMapper();
            json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(camelCaseCustomers);
            //System.out.println(json);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            myBatisUtils.closeSession();
            logger.info("Format on " + (System.currentTimeMillis() - startDt) + " millis");
        }
    }

    private static Map<String, Object> convertSnakeToCamel(Map<String, Object> originalMap) {
        Map<String, Object> newMap = new HashMap<>();
        for (Map.Entry<String, Object> entry : originalMap.entrySet()) {
            String camelCaseKey = convertSnakeToCamel(entry.getKey());
            newMap.put(camelCaseKey, entry.getValue());
        }
        return newMap;
    }

    private static String toCamelCase(String snakeCase) {
        StringBuilder result = new StringBuilder();
        String[] parts = snakeCase.split("_");
        for (int i = 0; i < parts.length; i++) {
            if (i == 0) {
                result.append(parts[i].toLowerCase()); // first word in lower case
            } else {
                result.append(parts[i].substring(0, 1).toUpperCase()); // capitalize the first letter
                result.append(parts[i].substring(1).toLowerCase()); // add the rest in lower case
            }
        }
        return result.toString();
    }

    private static String convertSnakeToCamel(String snakeCase){
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
