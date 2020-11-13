package com.thales.ui.server.uiserver.web.domain;

public class ToBeCheckSwitchDto {
    private Long id;
    private boolean checked;
    private String checkedComment;

    public ToBeCheckSwitchDto() {    }

    public ToBeCheckSwitchDto(Long id, String checkedComment) {
        this.id = id;
        this.checkedComment = checkedComment;
    }

    public ToBeCheckSwitchDto(Long id, boolean checked, String checkedComment) {
        this.id = id;
        this.checked = checked;
        this.checkedComment = checkedComment;
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

    @Override
    public String toString() {
        return "ToBeCheckSwitchDto{" +
                "id=" + id +
                ", checked=" + checked +
                ", checkedComment='" + checkedComment + '\'' +
                '}';
    }
}
