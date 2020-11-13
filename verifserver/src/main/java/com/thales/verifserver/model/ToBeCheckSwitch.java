package com.thales.verifserver.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
//defining class name as Table name
@Table(name = "to_be_check_switches")
public class ToBeCheckSwitch {
    //mark id as primary key
    @Id
    //defining id as column name
    @Column
    private Long id;

    @Column
    private boolean checked;

    @Column
    private String checkedComment;

    @Column
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
