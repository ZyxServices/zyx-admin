package com.zyx.model;

import javax.persistence.*;

/**
 * @author XiaoWei
 * @version V 1.0
 * @package com.zyx.model
 * Create by XiaoWei on 2016/8/2
 */
@Table(name = "circle_type")
public class CircleType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "type_name")
    private String typeName;

    private Integer state;//0为正常，-1为删除

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
