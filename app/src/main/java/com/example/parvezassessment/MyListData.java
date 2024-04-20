package com.example.parvezassessment;

public class MyListData{
    private String description;
    private String permission;
    public MyListData(String description, String permission) {
        this.description = description;
        this.permission = permission;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
