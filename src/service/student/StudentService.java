package service.student;

import model.student.Student;
import repository.student.StudentRepository;

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
        studentRepository.save();
    }

    public Student findById(String id) {
        return studentRepository.findById(id);
    }

    public Map<String, Student> getAllStudents() {
        return studentRepository.getAll();
    }

    public void updateStudent(Student student) {
        if (studentRepository.findById(student.getId()) == null) {
            throw new IllegalArgumentException("Student not found.");
        }
        studentRepository.save();
    }

    public void deleteStudent(String id) {
        if (studentRepository.findById(id) == null) {
            throw new IllegalArgumentException("Student not found.");
        }
        studentRepository.deleteById(id);
        studentRepository.save();
    }
}
