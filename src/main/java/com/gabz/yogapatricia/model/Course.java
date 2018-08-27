package com.gabz.yogapatricia.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
public class Course {

    @GeneratedValue
    @Id
    private int id;
    @ManyToOne
    @JoinColumn(name = "group_id")
    @NotNull
    private Group group;
    @NotNull
    private Date date;
    @ManyToMany
    private List<Student> students;

    public Course() {

    }

    public Course(Group group, Date date, List<Student> students) {
        this.group = group;
        this.date = date;
        this.students = students;
    }

    public int getId() {
        return id;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
