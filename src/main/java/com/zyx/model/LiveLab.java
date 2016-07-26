package com.zyx.model;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "t_live_lab")
public class LiveLab  implements Serializable{
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
    private String description;

    /**
     * 直播标签1-正常
     */
    private Integer state;

    /**
     * 记录状态
     */
    private Integer del;

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
     * @return description - 类型描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置类型描述
     *
     * @param description 类型描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取直播标签1-正常
     *
     * @return state - 直播标签1-正常
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置直播标签1-正常
     *
     * @param state 直播标签1-正常
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 获取记录状态
     *
     * @return del - 记录状态
     */
    public Integer getDel() {
        return del;
    }

    /**
     * 设置记录状态
     *
     * @param del 记录状态
     */
    public void setDel(Integer del) {
        this.del = del;
    }
}