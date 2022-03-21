package com.wg.basics.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "man")
public class ManUser {
    private String name;
    private String sex;
    private List<String> like;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public List<String> getLike() {
        return like;
    }

    public void setLike(List<String> like) {
        this.like = like;
    }

    @Override
    public String toString() {
        return "ManUser{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", like=" + like +
                '}';
    }
}
