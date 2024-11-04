package org.gen.config.mybatis.mapper;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.gen.config.mybatis.entity.Customers;

import java.util.List;
import java.util.Map;

public interface CustomersMapper {

    final String GET_ALL_CUSTOMERS = "SELECT * FROM bank_customers";

    final String GET_BY_LIMIT_RAND = "SELECT * from bank_customers_vb limit #{limit}";

    final String GET_CUSTOMERS_BY_CONSTRUCT = "${query}";

    @Select(GET_ALL_CUSTOMERS)
    @Results(value = {
            @Result(property = "id", column = "ID"),
            @Result(property = "customerName", column = "CUSTOMER_NAME"),
            @Result(property = "accountNumber", column = "ACCOUNT_NUMBER"),
            @Result(property = "accountType", column = "ACCOUNT_TYPE"),
            @Result(property = "balance", column = "BALANCE"),
            @Result(property = "branchCode", column = "BRANCH_CODE"),
            @Result(property = "branchName", column = "BRANCH_NAME"),
            @Result(property = "phoneNumber", column = "PHONE_NUMBER"),
            @Result(property = "email", column = "EMAIL"),
            @Result(property = "address", column = "ADDRESS"),
            @Result(property = "city", column = "CITY"),
            @Result(property = "state", column = "STATE"),
            @Result(property = "zipCode", column = "ZIP_CODE"),
            @Result(property = "country", column = "COUNTRY"),
            @Result(property = "dateOfBirth", column = "DATE_OF_BIRTH"),
            @Result(property = "accountOpenDate", column = "ACCOUNT_OPEN_DATE")
    })
    List<Customers> getAll();

    @Select(GET_BY_LIMIT_RAND)
    @Results(value = {
            @Result(property = "id", column = "ID"),
            @Result(property = "customerName", column = "CUSTOMER_NAME"),
            @Result(property = "accountNumber", column = "ACCOUNT_NUMBER"),
            @Result(property = "accountType", column = "ACCOUNT_TYPE"),
            @Result(property = "balance", column = "BALANCE"),
            @Result(property = "branchCode", column = "BRANCH_CODE"),
            @Result(property = "branchName", column = "BRANCH_NAME"),
            @Result(property = "phoneNumber", column = "PHONE_NUMBER"),
            @Result(property = "email", column = "EMAIL"),
            @Result(property = "address", column = "ADDRESS"),
            @Result(property = "city", column = "CITY"),
            @Result(property = "state", column = "STATE"),
            @Result(property = "zipCode", column = "ZIP_CODE"),
            @Result(property = "country", column = "COUNTRY"),
            @Result(property = "dateOfBirth", column = "DATE_OF_BIRTH"),
            @Result(property = "accountOpenDate", column = "ACCOUNT_OPEN_DATE")
    })
    List<Customers> getByLimit(int limit);

    @Select(GET_CUSTOMERS_BY_CONSTRUCT)
    @Results(value = {
            @Result(property = "id", column = "ID"),
            @Result(property = "customerName", column = "CUSTOMER_NAME"),
            @Result(property = "accountNumber", column = "ACCOUNT_NUMBER"),
            @Result(property = "accountType", column = "ACCOUNT_TYPE"),
            @Result(property = "balance", column = "BALANCE"),
            @Result(property = "branchCode", column = "BRANCH_CODE"),
            @Result(property = "branchName", column = "BRANCH_NAME"),
            @Result(property = "phoneNumber", column = "PHONE_NUMBER"),
            @Result(property = "email", column = "EMAIL"),
            @Result(property = "address", column = "ADDRESS"),
            @Result(property = "city", column = "CITY"),
            @Result(property = "state", column = "STATE"),
            @Result(property = "zipCode", column = "ZIP_CODE"),
            @Result(property = "country", column = "COUNTRY"),
            @Result(property = "dateOfBirth", column = "DATE_OF_BIRTH"),
            @Result(property = "accountOpenDate", column = "ACCOUNT_OPEN_DATE")
    })
    List<Customers> getByConstructQuery(String query);

    @Select(GET_BY_LIMIT_RAND)
    List<Map<String, Object>> getByLimitDynamic(int limit);

}
