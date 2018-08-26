package com.gabz.yogapatricia.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "course_group")
public class Group {

    @Id
    @GeneratedValue
    private int groupId;
    @NotEmpty
    @NotNull
    private String name;
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<Student> students;

    public Group() {

    }

    public Group(String name, List<Student> students) {
        this.name = name;
        this.students = students;
    }

    public int getGroupId() {
        return groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
