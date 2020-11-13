package com.example.workmanager.model;

public class RailSwitch {
    private int id;
    private boolean checked;
    private String checkedComment;

    public RailSwitch() {    }

    public RailSwitch(int id, boolean checked) {
        this.id = id;
        this.checked = checked;
    }

    public RailSwitch(int id, boolean checked, String checkedComment) {
        this.id = id;
        this.checkedComment = checkedComment;
        this.checked = checked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCheckedComment() {
        return checkedComment;
    }

    public void setCheckedComment(String checkedComment) {
        this.checkedComment = checkedComment;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
