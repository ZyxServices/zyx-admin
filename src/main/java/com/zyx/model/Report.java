package com.zyx.model;

import javax.persistence.*;

@Table(name = "t_report")
public class Report {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 举报对应模块
     */
    private Integer model;

    /**
     * 对应模块ID
     */
    @Column(name = "model_id")
    private Integer modelId;

    /**
     * 举报类型
     */
    private Integer lab;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取举报对应模块
     *
     * @return model - 举报对应模块
     */
    public Integer getModel() {
        return model;
    }

    /**
     * 设置举报对应模块
     *
     * @param model 举报对应模块
     */
    public void setModel(Integer model) {
        this.model = model;
    }

    /**
     * 获取对应模块ID
     *
     * @return model_id - 对应模块ID
     */
    public Integer getModelId() {
        return modelId;
    }

    /**
     * 设置对应模块ID
     *
     * @param modelId 对应模块ID
     */
    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    /**
     * 获取举报类型
     *
     * @return lab - 举报类型
     */
    public Integer getLab() {
        return lab;
    }

    /**
     * 设置举报类型
     *
     * @param lab 举报类型
     */
    public void setLab(Integer lab) {
        this.lab = lab;
    }
}