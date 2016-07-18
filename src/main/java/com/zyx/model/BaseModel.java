package com.zyx.model;

import org.apache.ibatis.type.JdbcType;
import tk.mybatis.mapper.annotation.ColumnType;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by SubDong on 16-7-12.
 *
 * @author SubDong
 * @version V1.0
 *          Copyright (c)2016 tyj-版权所有
 * @title BaseModel
 * @package com.zyx.model
 * @update 16-7-12 上午10:33
 */
public class BaseModel {

    /**
     * 主键ID
     **/
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnType(jdbcType = JdbcType.INTEGER)
    private Long id;

    /**
     * 创建时间
     **/
    @Column
    @ColumnType(jdbcType = JdbcType.BIGINT)
    private Long createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
