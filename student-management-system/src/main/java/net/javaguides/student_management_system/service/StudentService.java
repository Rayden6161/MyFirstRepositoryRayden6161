package net.javaguides.student_management_system.service;

import net.javaguides.student_management_system.entity.Student;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

public interface StudentService {
    List<Student>getAllStudents();
    Student saveStudent(Student student);
    Student getStudentById(Long id);

    Student updateStudent(Student student);

    void deleteStudentById(Long id);

    List<Student> searchStudents(String keyword);
}
