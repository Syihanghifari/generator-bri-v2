package org.gen.config.mybatis.mapper;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.gen.config.mybatis.entity.Finance;

import java.util.List;

public interface FinanceMapper {

    final String GET_ALL_FINANCE = "SELECT * FROM finance";

    final String GET_BY_LIMIT_RAND = "SELECT * from finance limit #{limit}";

    final String GET_FINANCE_BY_CONSTRUCT = "${query}";

    @Select(GET_ALL_FINANCE)
    @Results(value = {
            @Result(property = "account", column = "account"),
            @Result(property = "accountName", column = "accountName"),
            @Result(property = "amount", column = "amount"),
            @Result(property = "bic", column = "bic"),
            @Result(property = "creditCardCVV", column = "creditCardCVV"),
            @Result(property = "creditCardIssuer", column = "creditCardIssuer"),
            @Result(property = "creditCardNumber", column = "creditCardNumber"),
            @Result(property = "currencyCode", column = "currencyCode"),
            @Result(property = "currencyName", column = "currencyName"),
            @Result(property = "currencySymbol", column = "currencySymbol"),
            @Result(property = "ethereumAddress", column = "ethereumAddress"),
            @Result(property = "iban", column = "iban"),
            @Result(property = "mask", column = "mask"),
            @Result(property = "pin", column = "pin"),
            @Result(property = "routingNumber", column = "routingNumber"),
            @Result(property = "transactionDescription", column = "transactionDescription"),
            @Result(property = "transactionType", column = "transactionType")
    })
    List<Finance> getAll();

    @Select(GET_BY_LIMIT_RAND)
    @Results(value = {
            @Result(property = "account", column = "account"),
            @Result(property = "accountName", column = "accountName"),
            @Result(property = "amount", column = "amount"),
            @Result(property = "bic", column = "bic"),
            @Result(property = "creditCardCVV", column = "creditCardCVV"),
            @Result(property = "creditCardIssuer", column = "creditCardIssuer"),
            @Result(property = "creditCardNumber", column = "creditCardNumber"),
            @Result(property = "currencyCode", column = "currencyCode"),
            @Result(property = "currencyName", column = "currencyName"),
            @Result(property = "currencySymbol", column = "currencySymbol"),
            @Result(property = "ethereumAddress", column = "ethereumAddress"),
            @Result(property = "iban", column = "iban"),
            @Result(property = "mask", column = "mask"),
            @Result(property = "pin", column = "pin"),
            @Result(property = "routingNumber", column = "routingNumber"),
            @Result(property = "transactionDescription", column = "transactionDescription"),
            @Result(property = "transactionType", column = "transactionType")
    })
    List<Finance> getByLimit(int limit);

    @Select(GET_FINANCE_BY_CONSTRUCT)
    @Results(value = {
            @Result(property = "account", column = "account"),
            @Result(property = "accountName", column = "accountName"),
            @Result(property = "amount", column = "amount"),
            @Result(property = "bic", column = "bic"),
            @Result(property = "creditCardCVV", column = "creditCardCVV"),
            @Result(property = "creditCardIssuer", column = "creditCardIssuer"),
            @Result(property = "creditCardNumber", column = "creditCardNumber"),
            @Result(property = "currencyCode", column = "currencyCode"),
            @Result(property = "currencyName", column = "currencyName"),
            @Result(property = "currencySymbol", column = "currencySymbol"),
            @Result(property = "ethereumAddress", column = "ethereumAddress"),
            @Result(property = "iban", column = "iban"),
            @Result(property = "mask", column = "mask"),
            @Result(property = "pin", column = "pin"),
            @Result(property = "routingNumber", column = "routingNumber"),
            @Result(property = "transactionDescription", column = "transactionDescription"),
            @Result(property = "transactionType", column = "transactionType")
    })
    List<Finance> getByConstructQuery(String query);
}
