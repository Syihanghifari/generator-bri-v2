package org.gen;

import org.apache.ibatis.session.SqlSessionFactory;
import org.gen.config.mybatis.MyBatisUtils;
import org.gen.config.mybatis.mapper.DDLMapper;

import java.util.ArrayList;
import java.util.List;

public class Example {
    public static void main(String[] args) {
        String tableName = "bank_exmple";
        List<String> listDetailColumn = new ArrayList<>();
        listDetailColumn.add("sorting_id VARCHAR(100) PRIMARY KEY");
        listDetailColumn.add("is_array VARCHAR(100)");
        listDetailColumn.add("is_sort VARCHAR(100)");
        StringBuffer query = new StringBuffer();

        query.append("CREATE TABLE ").append(tableName).append(" (");

        int index = 1;
        for(String detailColumn : listDetailColumn){
            if(index != listDetailColumn.size())
                query.append(detailColumn).append(", ");
            if(index == listDetailColumn.size())
                query.append(detailColumn);
            index++;
        }
        query.append(")");
        System.out.println(query.toString());

        MyBatisUtils myBatisUtils = new MyBatisUtils();
        try{
            String driver = "com.mysql.cj.jdbc.Driver";
            String uri = "jdbc:mysql://20.167.40.12:3306/voltdb?allowPublicKeyRetrieval=true";
            String username = "voltage";
            String password = "Voltage123!";
            SqlSessionFactory sqlSessionFactory = myBatisUtils.createFactory(driver,uri,username,password);
            DDLMapper ddlMapper = myBatisUtils.createMapper(DDLMapper.class , sqlSessionFactory);
            ddlMapper.sqlConstruct(query.toString());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            myBatisUtils.commitSession();
            myBatisUtils.closeSession();
        }
    }
}
