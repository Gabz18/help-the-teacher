package com.gabz.yogapatricia.controller;

import com.gabz.yogapatricia.model.Course;
import com.gabz.yogapatricia.model.Group;
import com.gabz.yogapatricia.model.Student;
import com.gabz.yogapatricia.repository.CourseRepository;
import com.gabz.yogapatricia.repository.GroupRepository;
import com.gabz.yogapatricia.repository.StudentRepository;
import com.gabz.yogapatricia.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.expression.Lists;

import java.util.*;

@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private GroupRepository groupRepository;

    @GetMapping("")
    public String getCourses(Model model) {

        model.addAttribute("groups", groupRepository.findAll());
        List<Course> courses = courseRepository.findAll();
        Collections.reverse(courses);
        model.addAttribute("courses", courses);
        model.addAttribute("dateUtil", new DateUtil());

        return "course/index";
    }

    @GetMapping("/add/{id}")
    public String addCourseForm(@PathVariable Integer id, Model model) {

        Optional<Group> optionalGroup = groupRepository.findById(id);
        if (!optionalGroup.isPresent() || optionalGroup.get().getStudents().size() < 1) {
            return "redirect:/course";
        }

        List<Student> potentialStudents = optionalGroup.get().getStudents();
        model.addAttribute("potentialStudents", potentialStudents);
        model.addAttribute("group", optionalGroup.get());
        return "course/add-course";
    }

    @PostMapping("/add/{id}")
    public String submitCourseCreation(@PathVariable Integer id, @RequestParam("presentStudents") int[] studentIds) {

        Course course = new Course();
        List<Student> participants = new ArrayList<>();
        Optional<Group> selectedGroup = groupRepository.findById(id);
        course.setDate(new Date());

        for (int studentId : studentIds) {
            Optional<Student> student = studentRepository.findById(studentId);
            if (student.isPresent()) {
                participants.add(student.get());
            }
        }
        course.setStudents(participants);

        if (selectedGroup.isPresent()) {
            course.setGroup(selectedGroup.get());
        }

        courseRepository.save(course);

        return "redirect:/course";
    }
}
