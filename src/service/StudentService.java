package service;

import model.Student;
import repository.StudentRepository;

public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void addStudent(Student student) {
        if (studentRepository != null) {
            studentRepository.add(student);
        }
    }
}
