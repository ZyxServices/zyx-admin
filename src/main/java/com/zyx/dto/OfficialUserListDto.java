package com.zyx.dto;

import java.io.Serializable;

/**
 * Created by wms on 2016/7/27.
 *
 * @author WeiMinSheng
 * @version V1.0
 *          Copyright (c)2016 tyj-版权所有
 * @title OfficialUserListDto.java
 */
public class OfficialUserListDto implements Serializable {
    private Integer id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 昵称
     */
    private String nickname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
