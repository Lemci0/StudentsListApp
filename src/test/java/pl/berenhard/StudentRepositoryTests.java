package pl.berenhard;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import pl.berenhard.student.Student;
import pl.berenhard.student.StudentRepository;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class StudentRepositoryTests {

    @Autowired private StudentRepository repo;

    @Test
    public void addStudentTest() {

        //given
        Student student = new Student();
        student.setFirstName("Kamil");
        student.setLastName("Berenhard");
        student.setEmail("kamil.berenhard@gmail.com");
        student.setIndexNumber(1234);
        student.setDegreeCourse("Informatyka");
        student.setType("Zaoczne");

        //when
        Student savedStudent = repo.save(student);

        //then
        Assertions.assertThat(savedStudent).isNotNull();
        Assertions.assertThat(savedStudent.getId()).isGreaterThan(0);
    }

    @Test
    public void listAllTest() {

        //when
        Iterable<Student> students = repo.findAll();

        //then
        Assertions.assertThat(students).hasSizeGreaterThan(0);
    }

    @Test
    public void updateStudentTest() {

        //given
        Integer studentId = 1;

        //when
        Optional<Student> optionalStudent = repo.findById(studentId);
        Student student = optionalStudent.get();
        student.setType("Dzienne");
        repo.save(student);

        //then
        Student updatedStudent = repo.findById(studentId).get();
        Assertions.assertThat(updatedStudent.getType()).isEqualTo("Dzienne");

    }

    @Test
    public void getStudentTest() {

        //given
        Integer studentId = 1;

        //when
        Optional<Student> optionalStudent = repo.findById(studentId);

        //then
        Assertions.assertThat(optionalStudent).isPresent();
    }


}
