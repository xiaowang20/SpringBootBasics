package com.wg.basics.entity;

import java.util.List;

public class Menu {
    private Integer id;
    private String path;
    private List<Role> roles;

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", path='" + path + '\'' +
                ", roles=" + roles +
                '}';
    }
}
