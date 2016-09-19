package com.zyx.vo.deva;

import java.io.Serializable;

/**
 * Created by MrDeng on 2016/9/1.
 */
public class DevaModelVo  implements Serializable{
    private String modelTitle;
    private String nickname;
    private String official;

    public String getModelTitle() {
        return modelTitle;
    }

    public void setModelTitle(String modelTitle) {
        this.modelTitle = modelTitle;
    }

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
