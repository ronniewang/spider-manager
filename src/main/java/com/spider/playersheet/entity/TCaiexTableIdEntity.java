package com.spider.playersheet.entity;


/**
 * Created by ronnie on 2016/4/27.
 */
public class TCaiexTableIdEntity {

    private String tableName;

    private Long id;

    private Long tableId;

    public String getTableName() {

        return tableName;
    }

    public void setTableName(String tableName) {

        this.tableName = tableName;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public Long getTableId() {

        return tableId;
    }

    public void setTableId(Long tableId) {

        this.tableId = tableId;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TCaiexTableIdEntity that = (TCaiexTableIdEntity) o;

        if (id != that.id) return false;
        if (tableId != that.tableId) return false;
        if (tableName != null ? !tableName.equals(that.tableName) : that.tableName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {

        int result = tableName != null ? tableName.hashCode() : 0;
        result = 31 * result + (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (tableId ^ (tableId >>> 32));
        return result;
    }

    @Override
    public String toString() {

        return "TCaiexTableIdEntity{" +
                "tableName='" + tableName + '\'' +
                ", id=" + id +
                ", tableId=" + tableId +
                '}';
    }
}
