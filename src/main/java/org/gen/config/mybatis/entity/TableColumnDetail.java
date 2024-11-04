package org.gen.config.mybatis.entity;

public class TableColumnDetail {

    private String columnName;
    private String columnType;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    @Override
    public String toString() {
        return "TableColumnDetail{" +
                "columnName='" + columnName + '\'' +
                ", columnType='" + columnType + '\'' +
                '}';
    }
}
