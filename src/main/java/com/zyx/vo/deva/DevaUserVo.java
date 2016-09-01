package com.zyx.vo.deva;

import java.io.Serializable;

/**
 * Created by MrDeng on 2016/9/1.
 */
public class DevaUserVo implements Serializable{
    private String nickname;
    private String official;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getOfficial() {
        return official;
    }

    public void setOfficial(String official) {
        this.official = official;
    }
}
