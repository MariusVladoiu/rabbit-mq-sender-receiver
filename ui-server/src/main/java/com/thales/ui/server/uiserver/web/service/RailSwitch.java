package com.thales.ui.server.uiserver.web.service;

public class RailSwitch {
    private int id;
    private boolean checked;
    private String checkComment;

    public RailSwitch() {    }

    public RailSwitch(int id, boolean checked) {
        this.id = id;
        this.checked = checked;
    }

    public RailSwitch(int id, boolean checked, String checkComment) {
        this.id = id;
        this.checkComment = checkComment;
        this.checked = checked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCheckComment() {
        return checkComment;
    }

    public void setCheckComment(String checkComment) {
        this.checkComment = checkComment;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
