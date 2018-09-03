package com.gabz.yogapatricia.controller;

import com.gabz.yogapatricia.model.Group;
import com.gabz.yogapatricia.repository.CourseRepository;
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
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("")
    public String getGroups(Model model) {

        model.addAttribute("groups", groupRepository.findAll());
        model.addAttribute("newGroup", new Group());
        model.addAttribute("courseRepo", courseRepository);
        return "group/groups";
    }

    @PostMapping("")
    public String submitNewGroup(@ModelAttribute("newGroup") @Valid Group group, Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("newGroup", group);
            model.addAttribute("groups", groupRepository.findAll());
            return "group/groups";
        }

        groupRepository.save(group);
        return "redirect:/group";
    }

    @GetMapping("/remove/{id}")
    public String deleteGroup(@PathVariable(name = "id") Integer groupdId) {

        groupRepository.deleteById(groupdId);
        return "redirect:/group";
    }

    @GetMapping("/{id}/students")
    public String getGroupStudents(@PathVariable(name = "id") Integer groupId, Model model) {

        Optional<Group> group = groupRepository.findById(groupId);
        if (!group.isPresent()) {
            return "redirect:/group";
        }

        model.addAttribute("students", studentRepository.findStudentsByGroup(group.get()));
        model.addAttribute("title", "Groupe " + group.get().getName());
        return "student/student-list";
    }

    @PostMapping("/edit/{id}")
    @ResponseBody
    public boolean editGroup(@PathVariable(name = "id") Integer id, @RequestParam(name = "editedGroupName") String newGroupName) {

        Optional<Group> group = groupRepository.findById(id);
        if (newGroupName.length() > 2 && group.isPresent()) {
            Group upcomingModifications = group.get();
            upcomingModifications.setName(newGroupName);
            groupRepository.save(upcomingModifications);
        }
        return true;
    }

    @GetMapping("/{id}")
    public String getGroupDetail(@PathVariable Integer id, Model model) {

        Optional<Group> group = groupRepository.findById(id);

        if (!group.isPresent()) {
            return "redirect:/group";
        }

        model.addAttribute("group", group.get());
        model.addAttribute("courses", courseRepository.findByGroup(group.get()));
        return "group/group-detail";
    }
}
