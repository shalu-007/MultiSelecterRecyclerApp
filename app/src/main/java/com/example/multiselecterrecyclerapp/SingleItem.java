package com.example.multiselecterrecyclerapp;

public class SingleItem {
    private String title;
    private String description;
    private boolean active;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean pActive) {
        active = pActive;
    }

    public SingleItem(String pTitle, String pDescription, boolean pActive) {
        title = pTitle;
        description = pDescription;
        active = pActive;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String pTitle) {
        title = pTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String pDescription) {
        description = pDescription;
    }

    public SingleItem(String pTitle, String pDescription) {
        title = pTitle;
        description = pDescription;
    }
}
