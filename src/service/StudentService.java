package service;

import model.Student;
import repository.StudentRepository;

public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void addStudent(Student student) {
        // Business validation can be added here (e.g. check duplicate ID)
        if (studentRepository != null) {
            studentRepository.add(student);
        }
    }
}
