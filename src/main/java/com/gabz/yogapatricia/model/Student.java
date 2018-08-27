package com.gabz.yogapatricia.model;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Student {

    @Id
    @GeneratedValue
    private int studentId;
    private String firstname;
    @NotNull
    @Size(min = 3, message = "Champ requis")
    private String lastname;
    @Email(message = "Email Incorrect")
    private String email;
    @Pattern(regexp="(^$|[0-9]{10})", message = "Numéro Incorrect")
    private String phone;
    @ManyToOne
    @JoinColumn(name="group_id")
    private Group group;
    @ManyToMany
    private List<Course> courses;
    private String learningNotes;

    public Student() {

    }

    public Student(String firstname, String lastname, String email, String phone, Group group) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public String getLearningNotes() {
        return learningNotes;
    }

    public void setLearningNotes(String learningNotes) {
        this.learningNotes = learningNotes;
    }
}
