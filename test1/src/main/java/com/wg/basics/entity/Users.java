package com.wg.basics.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "people")
public class Users {
    private List<ManUser> users;

    public List<ManUser> getUsers() {
        return users;
    }

    public void setUsers(List<ManUser> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Users{" +
                "users=" + users +
                '}';
    }
}
