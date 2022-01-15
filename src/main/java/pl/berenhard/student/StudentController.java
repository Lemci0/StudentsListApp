package pl.berenhard.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/students/new")
    public String showNewForm(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("pageTitle", "Dodaj nowego studenta");
        return "student_form";
    }

    @PostMapping("/students/save")
    public String saveStudent(Student student) {
        service.save(student);

        return "redirect:/students";
    }

    @GetMapping("/students/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        try {
            Student student = service.get(id);
            model.addAttribute("student", student);
            model.addAttribute("pageTitle", "Edytuj studenta");
            return "student_form";
        } catch (StudentNotFoundException e) {
            return "redirect:/students";
        }
    }

}
