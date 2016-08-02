package com.zyx.model;

import javax.persistence.*;

@Table(name = "combined_data")
public class CombinedData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 组合id
     */
    @Column(name = "combinedId")
    private Integer combinedid;

    /**
     * 数据id（活动id）
     */
    @Column(name = "dataId")
    private Integer dataid;

    @Column(name = "create_time")
    private Long createTime;

    private Integer del;

    private Integer mask;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取组合id
     *
     * @return combinedId - 组合id
     */
    public Integer getCombinedid() {
        return combinedid;
    }

    /**
     * 设置组合id
     *
     * @param combinedid 组合id
     */
    public void setCombinedid(Integer combinedid) {
        this.combinedid = combinedid;
    }

    /**
     * 获取数据id（具体模块下的数据）
     *
     * @return dataId - 数据id（具体模块下的数据）
     */
    public Integer getDataid() {
        return dataid;
    }

    /**
     * 设置数据id（具体模块下的数据）
     *
     * @param dataid 数据id（具体模块下的数据）
     */
    public void setDataid(Integer dataid) {
        this.dataid = dataid;
    }

    /**
     * @return create_time
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /**
     * @return del
     */
    public Integer getDel() {
        return del;
    }

    /**
     * @param del
     */
    public void setDel(Integer del) {
        this.del = del;
    }

    /**
     * @return mask
     */
    public Integer getMask() {
        return mask;
    }

    /**
     * @param mask
     */
    public void setMask(Integer mask) {
        this.mask = mask;
    }
}