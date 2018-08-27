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
import java.util.Optional;

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
        model.addAttribute("title", "Liste des élèves");
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
    public String submitAddStudentForm(@ModelAttribute @Valid Student student, Errors errors, Model model, HttpServletRequest request) {

        if (errors.hasErrors()) {

            model.addAttribute(student);
            model.addAttribute("pageTitle", "Ajouter un Elève");
            model.addAttribute("delete", false);
            model.addAttribute("groups", groupRepository.findAll());
            return "student/add-student";
        }

        Optional<Group> group = groupRepository.findById(Integer.parseInt(request.getParameter("group")));
        if (group.isPresent()) {
            student.setGroup(group.get());
        }
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
                                       @ModelAttribute @Valid Student student,
                                       Errors errors,
                                       HttpServletRequest request) {

        if (errors.hasErrors()) {

            Student selectedStudent = studentRepository.findById(studentId).get();
            model.addAttribute(student);
            model.addAttribute("pageTitle", "Edit " + selectedStudent.getLastname());
            model.addAttribute("delete", true);
            model.addAttribute("groups", groupRepository.findAll());
            return "student/add-student";
        }

        Optional<Group> group = groupRepository.findById(Integer.parseInt(request.getParameter("group")));
        if (group.isPresent()) {
            student.setGroup(group.get());
        }
        studentRepository.deleteById(studentId);
        studentRepository.save(student);
        return "redirect:/student";
    }

    @GetMapping("/remove/{id}")
    public String deleteStudent(@PathVariable(name = "id") Integer studentId) {

        studentRepository.deleteById(studentId);
        return "redirect:/student";
    }

    @GetMapping("/get/{id}")
    @ResponseBody
    public Student getStudent(@PathVariable Integer id) {

        Optional<Student> student = studentRepository.findById(id);
        if (!student.isPresent()) {
            return null;
        }

        Student finalStudent = student.get();
        finalStudent.setGroup(null);
        finalStudent.setCourses(null);
        return student.get();
    }

    @PostMapping("/set-notes/{id}")
    @ResponseBody
    public boolean setNotes(@PathVariable Integer id, @RequestParam("notes") String notes) {

        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (!optionalStudent.isPresent()) {
            return false;
        }

        Student student = optionalStudent.get();
        student.setLearningNotes(notes);
        studentRepository.save(student);
        return true;
    }
}
