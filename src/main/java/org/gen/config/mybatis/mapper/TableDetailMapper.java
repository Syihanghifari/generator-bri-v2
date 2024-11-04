package org.gen.config.mybatis.mapper;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.gen.config.mybatis.entity.TableColumnDetail;
import org.gen.config.mybatis.entity.TableDetail;

import java.util.List;

public interface TableDetailMapper {

    final String GET_TABLE_DETAIL = "SHOW COLUMNS FROM ${tableName}";

    final String GET_COLUMN_TABLE_DETAIL = "SELECT COLUMN_NAME, COLUMN_TYPE FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = #{tableName} AND TABLE_SCHEMA = 'voltdb'";

    @Select(GET_TABLE_DETAIL)
    @Results(value = {
            @Result(property = "field", column = "FIELD"),
            @Result(property = "type", column = "TYPE"),
            @Result(property = "isNull", column = "NULL")
    })
    List<TableDetail> getTableDetail(String tableName);

    @Select(GET_COLUMN_TABLE_DETAIL)
    @Results(value = {
            @Result(property = "columnName", column = "COLUMN_NAME"),
            @Result(property = "columnType", column = "COLUMN_TYPE")
    })
    List<TableColumnDetail> getTableColumnDetail(String tableName);

}
