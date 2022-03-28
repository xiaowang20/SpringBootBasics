package com.wg.basics.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 *  资源实体类
 */
@Data
public class Menu implements Serializable {
    private Integer id;

    private String url;

    private String path;

    private String component;

    private String name;

    private String iconCls;

    private Meta meta;

    private Integer parentId;//指向父id

    private Boolean enabled;//显示
    private List<Menu> children;//资源List
}
