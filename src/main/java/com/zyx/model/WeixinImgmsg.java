package com.zyx.model;

import javax.persistence.*;

@Table(name = "WEIXIN_IMGMSG")
public class WeixinImgmsg {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "IMGMSG_ID")
    private String imgmsgId;

    /**
     * 关键词
     */
    @Column(name = "KEYWORD")
    private String keyword;

    /**
     * 创建时间
     */
    @Column(name = "CREATETIME")
    private String createtime;

    /**
     * 状态
     */
    @Column(name = "STATUS")
    private Integer status;

    /**
     * 备注
     */
    @Column(name = "BZ")
    private String bz;

    /**
     * 标题1
     */
    @Column(name = "TITLE1")
    private String title1;

    /**
     * 描述1
     */
    @Column(name = "DESCRIPTION1")
    private String description1;

    /**
     * 图片地址1
     */
    @Column(name = "IMGURL1")
    private String imgurl1;

    /**
     * 超链接1
     */
    @Column(name = "TOURL1")
    private String tourl1;

    /**
     * 标题2
     */
    @Column(name = "TITLE2")
    private String title2;

    /**
     * 描述2
     */
    @Column(name = "DESCRIPTION2")
    private String description2;

    /**
     * 图片地址2
     */
    @Column(name = "IMGURL2")
    private String imgurl2;

    /**
     * 超链接2
     */
    @Column(name = "TOURL2")
    private String tourl2;

    /**
     * 标题3
     */
    @Column(name = "TITLE3")
    private String title3;

    /**
     * 描述3
     */
    @Column(name = "DESCRIPTION3")
    private String description3;

    /**
     * 图片地址3
     */
    @Column(name = "IMGURL3")
    private String imgurl3;

    /**
     * 超链接3
     */
    @Column(name = "TOURL3")
    private String tourl3;

    /**
     * 标题4
     */
    @Column(name = "TITLE4")
    private String title4;

    /**
     * 描述4
     */
    @Column(name = "DESCRIPTION4")
    private String description4;

    /**
     * 图片地址4
     */
    @Column(name = "IMGURL4")
    private String imgurl4;

    /**
     * 超链接4
     */
    @Column(name = "TOURL4")
    private String tourl4;

    /**
     * 标题5
     */
    @Column(name = "TITLE5")
    private String title5;

    /**
     * 描述5
     */
    @Column(name = "DESCRIPTION5")
    private String description5;

    /**
     * 图片地址5
     */
    @Column(name = "IMGURL5")
    private String imgurl5;

    /**
     * 超链接5
     */
    @Column(name = "TOURL5")
    private String tourl5;

    /**
     * 标题6
     */
    @Column(name = "TITLE6")
    private String title6;

    /**
     * 描述6
     */
    @Column(name = "DESCRIPTION6")
    private String description6;

    /**
     * 图片地址6
     */
    @Column(name = "IMGURL6")
    private String imgurl6;

    /**
     * 超链接6
     */
    @Column(name = "TOURL6")
    private String tourl6;

    /**
     * 标题7
     */
    @Column(name = "TITLE7")
    private String title7;

    /**
     * 描述7
     */
    @Column(name = "DESCRIPTION7")
    private String description7;

    /**
     * 图片地址7
     */
    @Column(name = "IMGURL7")
    private String imgurl7;

    /**
     * 超链接7
     */
    @Column(name = "TOURL7")
    private String tourl7;

    /**
     * 标题8
     */
    @Column(name = "TITLE8")
    private String title8;

    /**
     * 描述8
     */
    @Column(name = "DESCRIPTION8")
    private String description8;

    /**
     * 图片地址8
     */
    @Column(name = "IMGURL8")
    private String imgurl8;

    /**
     * 超链接8
     */
    @Column(name = "TOURL8")
    private String tourl8;

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
     * @return IMGMSG_ID
     */
    public String getImgmsgId() {
        return imgmsgId;
    }

    /**
     * @param imgmsgId
     */
    public void setImgmsgId(String imgmsgId) {
        this.imgmsgId = imgmsgId;
    }

    /**
     * 获取关键词
     *
     * @return KEYWORD - 关键词
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * 设置关键词
     *
     * @param keyword 关键词
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword;
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
     * 获取状态
     *
     * @return STATUS - 状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态
     *
     * @param status 状态
     */
    public void setStatus(Integer status) {
        this.status = status;
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

    /**
     * 获取标题1
     *
     * @return TITLE1 - 标题1
     */
    public String getTitle1() {
        return title1;
    }

    /**
     * 设置标题1
     *
     * @param title1 标题1
     */
    public void setTitle1(String title1) {
        this.title1 = title1;
    }

    /**
     * 获取描述1
     *
     * @return DESCRIPTION1 - 描述1
     */
    public String getDescription1() {
        return description1;
    }

    /**
     * 设置描述1
     *
     * @param description1 描述1
     */
    public void setDescription1(String description1) {
        this.description1 = description1;
    }

    /**
     * 获取图片地址1
     *
     * @return IMGURL1 - 图片地址1
     */
    public String getImgurl1() {
        return imgurl1;
    }

    /**
     * 设置图片地址1
     *
     * @param imgurl1 图片地址1
     */
    public void setImgurl1(String imgurl1) {
        this.imgurl1 = imgurl1;
    }

    /**
     * 获取超链接1
     *
     * @return TOURL1 - 超链接1
     */
    public String getTourl1() {
        return tourl1;
    }

    /**
     * 设置超链接1
     *
     * @param tourl1 超链接1
     */
    public void setTourl1(String tourl1) {
        this.tourl1 = tourl1;
    }

    /**
     * 获取标题2
     *
     * @return TITLE2 - 标题2
     */
    public String getTitle2() {
        return title2;
    }

    /**
     * 设置标题2
     *
     * @param title2 标题2
     */
    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    /**
     * 获取描述2
     *
     * @return DESCRIPTION2 - 描述2
     */
    public String getDescription2() {
        return description2;
    }

    /**
     * 设置描述2
     *
     * @param description2 描述2
     */
    public void setDescription2(String description2) {
        this.description2 = description2;
    }

    /**
     * 获取图片地址2
     *
     * @return IMGURL2 - 图片地址2
     */
    public String getImgurl2() {
        return imgurl2;
    }

    /**
     * 设置图片地址2
     *
     * @param imgurl2 图片地址2
     */
    public void setImgurl2(String imgurl2) {
        this.imgurl2 = imgurl2;
    }

    /**
     * 获取超链接2
     *
     * @return TOURL2 - 超链接2
     */
    public String getTourl2() {
        return tourl2;
    }

    /**
     * 设置超链接2
     *
     * @param tourl2 超链接2
     */
    public void setTourl2(String tourl2) {
        this.tourl2 = tourl2;
    }

    /**
     * 获取标题3
     *
     * @return TITLE3 - 标题3
     */
    public String getTitle3() {
        return title3;
    }

    /**
     * 设置标题3
     *
     * @param title3 标题3
     */
    public void setTitle3(String title3) {
        this.title3 = title3;
    }

    /**
     * 获取描述3
     *
     * @return DESCRIPTION3 - 描述3
     */
    public String getDescription3() {
        return description3;
    }

    /**
     * 设置描述3
     *
     * @param description3 描述3
     */
    public void setDescription3(String description3) {
        this.description3 = description3;
    }

    /**
     * 获取图片地址3
     *
     * @return IMGURL3 - 图片地址3
     */
    public String getImgurl3() {
        return imgurl3;
    }

    /**
     * 设置图片地址3
     *
     * @param imgurl3 图片地址3
     */
    public void setImgurl3(String imgurl3) {
        this.imgurl3 = imgurl3;
    }

    /**
     * 获取超链接3
     *
     * @return TOURL3 - 超链接3
     */
    public String getTourl3() {
        return tourl3;
    }

    /**
     * 设置超链接3
     *
     * @param tourl3 超链接3
     */
    public void setTourl3(String tourl3) {
        this.tourl3 = tourl3;
    }

    /**
     * 获取标题4
     *
     * @return TITLE4 - 标题4
     */
    public String getTitle4() {
        return title4;
    }

    /**
     * 设置标题4
     *
     * @param title4 标题4
     */
    public void setTitle4(String title4) {
        this.title4 = title4;
    }

    /**
     * 获取描述4
     *
     * @return DESCRIPTION4 - 描述4
     */
    public String getDescription4() {
        return description4;
    }

    /**
     * 设置描述4
     *
     * @param description4 描述4
     */
    public void setDescription4(String description4) {
        this.description4 = description4;
    }

    /**
     * 获取图片地址4
     *
     * @return IMGURL4 - 图片地址4
     */
    public String getImgurl4() {
        return imgurl4;
    }

    /**
     * 设置图片地址4
     *
     * @param imgurl4 图片地址4
     */
    public void setImgurl4(String imgurl4) {
        this.imgurl4 = imgurl4;
    }

    /**
     * 获取超链接4
     *
     * @return TOURL4 - 超链接4
     */
    public String getTourl4() {
        return tourl4;
    }

    /**
     * 设置超链接4
     *
     * @param tourl4 超链接4
     */
    public void setTourl4(String tourl4) {
        this.tourl4 = tourl4;
    }

    /**
     * 获取标题5
     *
     * @return TITLE5 - 标题5
     */
    public String getTitle5() {
        return title5;
    }

    /**
     * 设置标题5
     *
     * @param title5 标题5
     */
    public void setTitle5(String title5) {
        this.title5 = title5;
    }

    /**
     * 获取描述5
     *
     * @return DESCRIPTION5 - 描述5
     */
    public String getDescription5() {
        return description5;
    }

    /**
     * 设置描述5
     *
     * @param description5 描述5
     */
    public void setDescription5(String description5) {
        this.description5 = description5;
    }

    /**
     * 获取图片地址5
     *
     * @return IMGURL5 - 图片地址5
     */
    public String getImgurl5() {
        return imgurl5;
    }

    /**
     * 设置图片地址5
     *
     * @param imgurl5 图片地址5
     */
    public void setImgurl5(String imgurl5) {
        this.imgurl5 = imgurl5;
    }

    /**
     * 获取超链接5
     *
     * @return TOURL5 - 超链接5
     */
    public String getTourl5() {
        return tourl5;
    }

    /**
     * 设置超链接5
     *
     * @param tourl5 超链接5
     */
    public void setTourl5(String tourl5) {
        this.tourl5 = tourl5;
    }

    /**
     * 获取标题6
     *
     * @return TITLE6 - 标题6
     */
    public String getTitle6() {
        return title6;
    }

    /**
     * 设置标题6
     *
     * @param title6 标题6
     */
    public void setTitle6(String title6) {
        this.title6 = title6;
    }

    /**
     * 获取描述6
     *
     * @return DESCRIPTION6 - 描述6
     */
    public String getDescription6() {
        return description6;
    }

    /**
     * 设置描述6
     *
     * @param description6 描述6
     */
    public void setDescription6(String description6) {
        this.description6 = description6;
    }

    /**
     * 获取图片地址6
     *
     * @return IMGURL6 - 图片地址6
     */
    public String getImgurl6() {
        return imgurl6;
    }

    /**
     * 设置图片地址6
     *
     * @param imgurl6 图片地址6
     */
    public void setImgurl6(String imgurl6) {
        this.imgurl6 = imgurl6;
    }

    /**
     * 获取超链接6
     *
     * @return TOURL6 - 超链接6
     */
    public String getTourl6() {
        return tourl6;
    }

    /**
     * 设置超链接6
     *
     * @param tourl6 超链接6
     */
    public void setTourl6(String tourl6) {
        this.tourl6 = tourl6;
    }

    /**
     * 获取标题7
     *
     * @return TITLE7 - 标题7
     */
    public String getTitle7() {
        return title7;
    }

    /**
     * 设置标题7
     *
     * @param title7 标题7
     */
    public void setTitle7(String title7) {
        this.title7 = title7;
    }

    /**
     * 获取描述7
     *
     * @return DESCRIPTION7 - 描述7
     */
    public String getDescription7() {
        return description7;
    }

    /**
     * 设置描述7
     *
     * @param description7 描述7
     */
    public void setDescription7(String description7) {
        this.description7 = description7;
    }

    /**
     * 获取图片地址7
     *
     * @return IMGURL7 - 图片地址7
     */
    public String getImgurl7() {
        return imgurl7;
    }

    /**
     * 设置图片地址7
     *
     * @param imgurl7 图片地址7
     */
    public void setImgurl7(String imgurl7) {
        this.imgurl7 = imgurl7;
    }

    /**
     * 获取超链接7
     *
     * @return TOURL7 - 超链接7
     */
    public String getTourl7() {
        return tourl7;
    }

    /**
     * 设置超链接7
     *
     * @param tourl7 超链接7
     */
    public void setTourl7(String tourl7) {
        this.tourl7 = tourl7;
    }

    /**
     * 获取标题8
     *
     * @return TITLE8 - 标题8
     */
    public String getTitle8() {
        return title8;
    }

    /**
     * 设置标题8
     *
     * @param title8 标题8
     */
    public void setTitle8(String title8) {
        this.title8 = title8;
    }

    /**
     * 获取描述8
     *
     * @return DESCRIPTION8 - 描述8
     */
    public String getDescription8() {
        return description8;
    }

    /**
     * 设置描述8
     *
     * @param description8 描述8
     */
    public void setDescription8(String description8) {
        this.description8 = description8;
    }

    /**
     * 获取图片地址8
     *
     * @return IMGURL8 - 图片地址8
     */
    public String getImgurl8() {
        return imgurl8;
    }

    /**
     * 设置图片地址8
     *
     * @param imgurl8 图片地址8
     */
    public void setImgurl8(String imgurl8) {
        this.imgurl8 = imgurl8;
    }

    /**
     * 获取超链接8
     *
     * @return TOURL8 - 超链接8
     */
    public String getTourl8() {
        return tourl8;
    }

    /**
     * 设置超链接8
     *
     * @param tourl8 超链接8
     */
    public void setTourl8(String tourl8) {
        this.tourl8 = tourl8;
    }
}