package service;

import model.Student;
import repository.StudentRepository;

import java.util.Map;

public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void addStudent(Student student) {
        if (studentRepository.findById(student.getId()) != null) {
            throw new IllegalArgumentException("Student ID already exists.");
        }
        studentRepository.add(student);
    }

    public Map<String, Student> getAllStudents() {
        return studentRepository.getAll();
    }
}
