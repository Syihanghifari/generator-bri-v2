package org.gen.config.mybatis.mapper;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface DDLMapper {
    final String QUERY_CONSTRUCT = "${query}";

    final String CREATE_TABLE_EXAMPLE = "CREATE TABLE SORTING_CONFIG (sorting_id VARCHAR(100) PRIMARY KEY, is_array VARCHAR(100), is_sort VARCHAR(100), ascdesc VARCHAR(100), sort_by_fieldName VARCHAR(100))";

    final String GET_ALL_TABLE_NAME = "SELECT TABLE_NAME FROM information_schema.tables WHERE TABLE_SCHEMA = 'voltdb' AND TABLE_TYPE = 'BASE TABLE'";

    @Update(CREATE_TABLE_EXAMPLE)
    void createTable();

    @Update(QUERY_CONSTRUCT)
    void sqlConstruct(String query);

    @Select(GET_ALL_TABLE_NAME)
    List<String> getAllTableName();
}
