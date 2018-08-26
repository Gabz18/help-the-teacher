package com.gabz.yogapatricia.controller;


import com.gabz.yogapatricia.model.Student;
import com.gabz.yogapatricia.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("")
    public String getStudentList(Model model) {

        model.addAttribute("students", studentRepository.findAll());
        return "student/student-list";
    }

    @GetMapping("/add")
    public String getAddStudentForm(Model model) {

        model.addAttribute(new Student());
        return "student/add-student";
    }

    @PostMapping("/add")
    public String submitAddStudentForm(@ModelAttribute @Valid Student student, Model model, Errors errors) {

        if (errors.hasErrors()) {

            model.addAttribute(student);
            return "student/add-student";
        }

        studentRepository.save(student);
        return "redirect:/student";
    }
}
