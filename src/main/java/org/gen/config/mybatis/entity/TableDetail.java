package org.gen.config.mybatis.entity;

public class TableDetail {
    private String field;
    private String type;
    private String isNull;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIsNull() {
        return isNull;
    }

    public void setIsNull(String isNull) {
        this.isNull = isNull;
    }

    @Override
    public String toString() {
        return "TableDetail{" +
                "field='" + field + '\'' +
                ", type='" + type + '\'' +
                ", isNull='" + isNull + '\'' +
                '}';
    }
}
