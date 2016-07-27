package com.zyx.model;

import javax.persistence.*;

@Table(name = "combination")
public class Combination {
    /**
     * 表id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 组合名称
     */
    private String name;

    /**
     * 组合封面图片
     */
    private String image;

    /**
     * 组合创建时间
     */
    @Column(name = "create_time")
    private Long createTime;

    /**
     * 删除（0，正常  1，删除）
     */
    private Integer del;

    /**
     * 屏蔽（0，正常，1屏蔽）
     */
    private Integer mask;

    /**
     * 获取表id
     *
     * @return id - 表id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置表id
     *
     * @param id 表id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取组合名称
     *
     * @return name - 组合名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置组合名称
     *
     * @param name 组合名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取组合封面图片
     *
     * @return image - 组合封面图片
     */
    public String getImage() {
        return image;
    }

    /**
     * 设置组合封面图片
     *
     * @param image 组合封面图片
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * 获取组合创建时间
     *
     * @return create_time - 组合创建时间
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * 设置组合创建时间
     *
     * @param createTime 组合创建时间
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取删除（0，正常  1，删除）
     *
     * @return del - 删除（0，正常  1，删除）
     */
    public Integer getDel() {
        return del;
    }

    /**
     * 设置删除（0，正常  1，删除）
     *
     * @param del 删除（0，正常  1，删除）
     */
    public void setDel(Integer del) {
        this.del = del;
    }

    /**
     * 获取屏蔽（0，正常，1屏蔽）
     *
     * @return mask - 屏蔽（0，正常，1屏蔽）
     */
    public Integer getMask() {
        return mask;
    }

    /**
     * 设置屏蔽（0，正常，1屏蔽）
     *
     * @param mask 屏蔽（0，正常，1屏蔽）
     */
    public void setMask(Integer mask) {
        this.mask = mask;
    }
}