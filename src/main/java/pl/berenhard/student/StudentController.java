package pl.berenhard.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class StudentController {

    @Autowired private StudentService service;

    @GetMapping("/students")
    public String showStudentsList(Model model) {
        List<Student> listStudents = service.listAll();
        model.addAttribute("listStudents", listStudents);

        return "students";
    }
}