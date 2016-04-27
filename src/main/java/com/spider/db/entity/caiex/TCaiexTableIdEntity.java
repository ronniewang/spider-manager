package com.spider.db.entity.caiex;

import javax.persistence.*;

/**
 * Created by ronnie on 2016/4/27.
 */
@Entity
@Table(name = "t_caiex_table_id")
public class TCaiexTableIdEntity {

    private String tableName;

    private Long id;

    private Long tableId;

    @Basic
    @Column(name = "table_name", nullable = false, length = 255)
    public String getTableName() {

        return tableName;
    }

    public void setTableName(String tableName) {

        this.tableName = tableName;
    }

    @Id
    @Column(name = "id", nullable = false)
    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    @Basic
    @Column(name = "table_id", nullable = false)
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
}
