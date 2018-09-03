package com.gabz.yogapatricia.model;

import java.util.List;

public class CourseReport {

    List<Student> participants;
    List<Student> missingStudents;

    public CourseReport setObjectAjaxFriendly() {

        for (int i = 0; i < this.participants.size(); i++) {
            Student particpant = participants.get(i);
            particpant.setGroup(null);
            particpant.setCourses(null);
        }

        for (int j = 0; j < this.missingStudents.size(); j++) {
            Student missing = missingStudents.get(j);
            missing.setGroup(null);
            missing.setCourses(null);
        }

        return this;
    }

    public List<Student> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Student> participants) {
        this.participants = participants;
    }

    public List<Student> getMissingStudents() {
        return missingStudents;
    }

    public void setMissingStudents(List<Student> missingStudents) {
        this.missingStudents = missingStudents;
    }
}
