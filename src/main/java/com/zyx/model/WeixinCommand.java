package com.zyx.model;

import javax.persistence.*;

@Table(name = "WEIXIN_COMMAND")
public class WeixinCommand {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "COMMAND_ID")
    private String commandId;

    /**
     * 关键词
     */
    @Column(name = "KEYWORD")
    private String keyword;

    /**
     * 应用路径
     */
    @Column(name = "COMMANDCODE")
    private String commandcode;

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
     * @return COMMAND_ID
     */
    public String getCommandId() {
        return commandId;
    }

    /**
     * @param commandId
     */
    public void setCommandId(String commandId) {
        this.commandId = commandId;
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
     * 获取应用路径
     *
     * @return COMMANDCODE - 应用路径
     */
    public String getCommandcode() {
        return commandcode;
    }

    /**
     * 设置应用路径
     *
     * @param commandcode 应用路径
     */
    public void setCommandcode(String commandcode) {
        this.commandcode = commandcode;
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
}