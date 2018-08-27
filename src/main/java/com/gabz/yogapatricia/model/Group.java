package com.gabz.yogapatricia.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "learning_group")
public class Group {

    @Id
    @GeneratedValue
    private int groupId;
    @Size(min = 3, message = "Champ requis")
    private String name;
    @OneToMany(mappedBy = "group", cascade = CascadeType.DETACH)
    private List<Student> students;
    @OneToMany(mappedBy = "group", cascade = CascadeType.DETACH)
    private List<Course> courses;

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
