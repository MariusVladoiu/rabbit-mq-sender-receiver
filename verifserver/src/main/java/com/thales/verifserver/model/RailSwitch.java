package com.thales.verifserver.model;

import javax.persistence.*;

@Entity
//defining class name as Table name
//@Table
@Table(name = "rail_switches")
//@SequenceGenerator(name = "SECQNAMEINENTITY", sequenceName = "DB_SECQ NAME", allocationSize = 1)
public class RailSwitch {

    //mark id as primary key
    @Id
    //defining id as column name
    //@Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@GeneratedValue
    private Long id;

    @Column
    private boolean checked;

    @Column
    private String checkedComment;

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
}
