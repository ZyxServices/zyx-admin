package com.zyx.model;

import javax.persistence.*;

@Table(name = "SYS_MENU")
public class SysMenu {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "MENU_ID")
    private Integer menuId;

    @Column(name = "MENU_NAME")
    private String menuName;

    @Column(name = "MENU_URL")
    private String menuUrl;

    @Column(name = "PARENT_ID")
    private String parentId;

    @Column(name = "MENU_ORDER")
    private String menuOrder;

    @Column(name = "MENU_ICON")
    private String menuIcon;

    @Column(name = "MENU_TYPE")
    private String menuType;

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
     * @return MENU_ID
     */
    public Integer getMenuId() {
        return menuId;
    }

    /**
     * @param menuId
     */
    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    /**
     * @return MENU_NAME
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * @param menuName
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    /**
     * @return MENU_URL
     */
    public String getMenuUrl() {
        return menuUrl;
    }

    /**
     * @param menuUrl
     */
    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    /**
     * @return PARENT_ID
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * @param parentId
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * @return MENU_ORDER
     */
    public String getMenuOrder() {
        return menuOrder;
    }

    /**
     * @param menuOrder
     */
    public void setMenuOrder(String menuOrder) {
        this.menuOrder = menuOrder;
    }

    /**
     * @return MENU_ICON
     */
    public String getMenuIcon() {
        return menuIcon;
    }

    /**
     * @param menuIcon
     */
    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    /**
     * @return MENU_TYPE
     */
    public String getMenuType() {
        return menuType;
    }

    /**
     * @param menuType
     */
    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }
}