package com.zyx.parm.deva;

/**
 * Created by MrDeng on 2016/8/26.
 */
public class DevaParam {

    Integer area;
    Integer model;
    Integer modelId;

    public DevaParam() {
    }

    public DevaParam(Integer area, Integer model, Integer modelId) {
        this.area = area;
        this.model = model;
        this.modelId = modelId;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }
    public Integer getModel() {
        return model;
    }

    public void setModel(Integer model) {
        this.model = model;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }
}
