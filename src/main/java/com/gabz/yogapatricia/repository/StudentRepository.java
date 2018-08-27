package com.gabz.yogapatricia.repository;

import com.gabz.yogapatricia.model.Group;
import com.gabz.yogapatricia.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findStudentsByGroup(Group group);
}
