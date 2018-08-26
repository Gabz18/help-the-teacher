package com.gabz.yogapatricia.controller;


import com.gabz.yogapatricia.model.Group;
import com.gabz.yogapatricia.model.Student;
import com.gabz.yogapatricia.repository.GroupRepository;
import com.gabz.yogapatricia.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private GroupRepository groupRepository;

    @GetMapping("")
    public String getStudentList(Model model) {

        model.addAttribute("students", studentRepository.findAll());
        return "student/student-list";
    }

    @GetMapping("/add")
    public String getAddStudentForm(Model model) {

        model.addAttribute(new Student());
        model.addAttribute("pageTitle", "Ajouter un Elève");
        model.addAttribute("delete", false);
        model.addAttribute("groups", groupRepository.findAll());
        return "student/add-student";
    }

    @PostMapping("/add")
    public String submitAddStudentForm(@ModelAttribute @Valid Student student, Model model, Errors errors, HttpServletRequest request) {

        if (errors.hasErrors()) {

            model.addAttribute(student);
            model.addAttribute("pageTitle", "Ajouter un Elève");
            model.addAttribute("delete", false);
            model.addAttribute("groups", groupRepository.findAll());
            return "student/add-student";
        }

        Group group = groupRepository.findById(Integer.parseInt(request.getParameter("group"))).get();
        student.setGroup(group);
        studentRepository.save(student);
        return "redirect:/student";
    }

    @GetMapping("/edit/{id}")
    public String editStudent(@PathVariable(name = "id") Integer studentId, Model model) {

        Student selectedStudent = new Student();
        selectedStudent = studentRepository.findById(studentId).get();
        model.addAttribute("pageTitle", "Edit " + selectedStudent.getLastname());
        model.addAttribute("delete", true);
        model.addAttribute("student", selectedStudent);
        model.addAttribute("groups", groupRepository.findAll());
        return "student/add-student";
    }

    @PostMapping("/edit/{id}")
    public String submitStudentEdition(@PathVariable(name = "id") Integer studentId,
                                       Model model,
                                       Errors errors,
                                       @ModelAttribute @Valid Student student,
                                       HttpServletRequest request) {

        if (errors.hasErrors()) {

            Student selectedStudent = studentRepository.findById(studentId).get();
            model.addAttribute(student);
            model.addAttribute("pageTitle", "Edit " + selectedStudent.getLastname());
            model.addAttribute("delete", true);
            model.addAttribute("groups", groupRepository.findAll());
            return "student/add-student";
        }

        Group group = groupRepository.findById(Integer.parseInt(request.getParameter("group"))).get();
        student.setGroup(group);
        studentRepository.deleteById(studentId);
        studentRepository.save(student);
        return "redirect:/student";
    }

    @GetMapping("/remove/{id}")
    public String deleteStudent(@PathVariable(name = "id") Integer studentId) {

        studentRepository.deleteById(studentId);
        return "redirect:/student";
    }

}
