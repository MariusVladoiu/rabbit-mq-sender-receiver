package com.example.workmanager.model;

public class ToBeCheckSwitch {
    private Long id;
    private boolean checked;
    private String checkedComment;
    private Long groupId;

    public ToBeCheckSwitch() {    }

    public ToBeCheckSwitch(Long id, boolean checked, String checkedComment, Long groupId) {
        this.id = id;
        this.checked = checked;
        this.checkedComment = checkedComment;
        this.groupId = groupId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getCheckedComment() {
        return checkedComment;
    }

    public void setCheckedComment(String checkedComment) {
        this.checkedComment = checkedComment;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
