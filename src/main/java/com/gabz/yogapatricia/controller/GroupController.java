package com.gabz.yogapatricia.controller;

import com.gabz.yogapatricia.model.Group;
import com.gabz.yogapatricia.repository.GroupRepository;
import com.gabz.yogapatricia.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("")
    public String getGroups(Model model) {

        model.addAttribute("groups", groupRepository.findAll());
        model.addAttribute("newGroup", new Group());
        return "student/groups";
    }

    @PostMapping("")
    public String sumitNewGroup(@ModelAttribute @Valid Group group, Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("newGroup", group);
            model.addAttribute("groups", groupRepository.findAll());
            return "redirect:/group";
        }

        groupRepository.save(group);
        return "redirect:/group";
    }

    @GetMapping("/remove/{id}")
    public String deleteGroup(@PathVariable(name = "id") Integer groupdId) {

        groupRepository.deleteById(groupdId);
        return "redirect:/group";
    }
}
