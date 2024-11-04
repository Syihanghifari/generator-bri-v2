package org.gen;

import org.apache.ibatis.session.SqlSessionFactory;
import org.gen.config.mybatis.MyBatisUtils;
import org.gen.config.mybatis.entity.Customers;
import org.gen.config.mybatis.entity.Finance;
import org.gen.config.mybatis.mapper.CustomersMapper;
import org.gen.config.mybatis.mapper.FinanceMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class SdkTest {
    private static Logger logger = Logger.getLogger("Example");

    public static void main(String[] args) {
        long startDt = System.currentTimeMillis();
        String driver = "com.mysql.cj.jdbc.Driver";
        String uri = "jdbc:mysql://20.167.40.12:3306/voltdb?allowPublicKeyRetrieval=true";
        String username = "voltage";
        String password = "Voltage123!";

        MyBatisUtils myBatisUtils = new MyBatisUtils();
        List<Finance> listFinance = new ArrayList<>();
        List<Customers> listCustomers = new ArrayList<>();

        try{
            SqlSessionFactory sqlSessionFactory = myBatisUtils.createFactory(driver,uri,username,password);
//            FinanceMapper financeMapper = myBatisUtils.createMapper(FinanceMapper.class, sqlSessionFactory);
//            listFinance = financeMapper.getByLimit(100000);

            CustomersMapper customersMapper = myBatisUtils.createMapper(CustomersMapper.class, sqlSessionFactory);
            listCustomers = customersMapper.getByLimit(1000000);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            myBatisUtils.closeSession();
        }
//        for(Finance finance : listFinance){
//            System.out.println(finance);
//        }
        logger.info("Format on " + (System.currentTimeMillis() - startDt) + " millis");
    }
}
