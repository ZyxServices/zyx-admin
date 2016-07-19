package com.zyx.model;

import javax.persistence.*;

@Table(name = "t_live_lab")
public class LiveLab {
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Long createTime;

    /**
     * 直播类型
     */
    private String lab;

    /**
     * 类型描述
     */
    private String desc;

    /**
     * 直播标签1-正常
     */
    private Integer status;

    /**
     * 获取主键ID
     *
     * @return id - 主键ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键ID
     *
     * @param id 主键ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取直播类型
     *
     * @return lab - 直播类型
     */
    public String getLab() {
        return lab;
    }

    /**
     * 设置直播类型
     *
     * @param lab 直播类型
     */
    public void setLab(String lab) {
        this.lab = lab;
    }

    /**
     * 获取类型描述
     *
     * @return desc - 类型描述
     */
    public String getDesc() {
        return desc;
    }

    /**
     * 设置类型描述
     *
     * @param desc 类型描述
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * 获取直播标签1-正常
     *
     * @return status - 直播标签1-正常
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置直播标签1-正常
     *
     * @param status 直播标签1-正常
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}