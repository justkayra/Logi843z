package com.semantyca.model;

import com.semantyca.model.user.User;

import java.util.UUID;

public class Assignee extends DataEntity<UUID> {
    private String name;
    private User user;
    private int rank;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public Integer getUserId() {
        return user.id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
