package net.javaguides.student_management_system.service.impl;

import net.javaguides.student_management_system.entity.Student;
import net.javaguides.student_management_system.repository.StudentRepository;
import net.javaguides.student_management_system.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentServiceImpl implements StudentService {
    StudentRepository studentRepository;
    @Autowired
    StudentServiceImpl(StudentRepository studentRepository){

        this.studentRepository=studentRepository;
    }
    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).get();
        //ovaj get() smo morali dodati!
    }

    @Override
    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);

    }

    @Override
    public List<Student> searchStudents(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return studentRepository.findAll();
        } else {
            return studentRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmailContainingIgnoreCase(keyword, keyword, keyword);
        }
    }
    }

