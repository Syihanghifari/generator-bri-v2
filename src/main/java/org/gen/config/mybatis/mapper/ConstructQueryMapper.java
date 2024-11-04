package org.gen.config.mybatis.mapper;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface ConstructQueryMapper {
    final String QUERY_CONSTRUCT = "${query}";

    @Select(QUERY_CONSTRUCT)
    List<Map<String, Object>> getByLimitDynamic(String query);
}
