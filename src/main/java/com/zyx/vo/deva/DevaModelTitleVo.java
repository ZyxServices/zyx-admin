package com.zyx.vo.deva;

/**
 * Created by MrDeng on 2016/8/26.
 */
public class DevaModelTitleVo extends DevaVo{
    private String modelTitle;

    DevaUserVo devaUserVo;

    public DevaUserVo getDevaUserVo() {
        return devaUserVo;
    }

    public void setDevaUserVo(DevaUserVo devaUserVo) {
        this.devaUserVo = devaUserVo;
    }

    public String getModelTitle() {
        return modelTitle;
    }

    public void setModelTitle(String modelTitle) {
        this.modelTitle = modelTitle;
    }
}
