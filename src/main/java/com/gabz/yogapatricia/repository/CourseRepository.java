package com.gabz.yogapatricia.repository;

import com.gabz.yogapatricia.model.Course;
import com.gabz.yogapatricia.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    List<Course> findByGroup(Group group);
}
