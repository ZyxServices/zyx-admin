package com.zyx.model;

import javax.persistence.*;

@Table(name = "TD_PICTURE")
public class TdPicture {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "PICTURES_ID")
    private String picturesId;

    /**
     * 标题
     */
    @Column(name = "TITLE")
    private String title;

    /**
     * 文件名
     */
    @Column(name = "NAME")
    private String name;

    /**
     * 路径
     */
    @Column(name = "PATH")
    private String path;

    /**
     * 创建时间
     */
    @Column(name = "CREATETIME")
    private String createtime;

    /**
     * 属于
     */
    @Column(name = "MASTER_ID")
    private String masterId;

    /**
     * 备注
     */
    @Column(name = "BZ")
    private String bz;

    /**
     * @return ID
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
     * @return PICTURES_ID
     */
    public String getPicturesId() {
        return picturesId;
    }

    /**
     * @param picturesId
     */
    public void setPicturesId(String picturesId) {
        this.picturesId = picturesId;
    }

    /**
     * 获取标题
     *
     * @return TITLE - 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取文件名
     *
     * @return NAME - 文件名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置文件名
     *
     * @param name 文件名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取路径
     *
     * @return PATH - 路径
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置路径
     *
     * @param path 路径
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 获取创建时间
     *
     * @return CREATETIME - 创建时间
     */
    public String getCreatetime() {
        return createtime;
    }

    /**
     * 设置创建时间
     *
     * @param createtime 创建时间
     */
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    /**
     * 获取属于
     *
     * @return MASTER_ID - 属于
     */
    public String getMasterId() {
        return masterId;
    }

    /**
     * 设置属于
     *
     * @param masterId 属于
     */
    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }

    /**
     * 获取备注
     *
     * @return BZ - 备注
     */
    public String getBz() {
        return bz;
    }

    /**
     * 设置备注
     *
     * @param bz 备注
     */
    public void setBz(String bz) {
        this.bz = bz;
    }
}