package controller;

import model.Student;
import repository.StudentRepository;
import service.StudentService;
import view.ConsoleColor;
import view.MenuStudentManageView;

import java.util.Map;

public class StudentManageController {
    private final MenuStudentManageView menuStudentManageView;
    private final StudentService studentService;
    private final StudentRepository studentRepository;
    private final Map<String, Student> students;

    public StudentManageController(MenuStudentManageView menuStudentManageView, StudentService studentService, StudentRepository studentRepository, Map<String, Student> students) {
        this.menuStudentManageView = menuStudentManageView;
        this.studentService = studentService;
        this.studentRepository = studentRepository;
        this.students = students;
    }

    public void run() {
        while (true) {
            menuStudentManageView.showMenu();
            int choice = menuStudentManageView.inputChoice(0, 4);
            String id;
            Student student;
            switch (choice) {

                case 1:
                    student = menuStudentManageView.inputStudentData();
                    try {
                        studentService.addStudent(student);
                        ConsoleColor.printSuccess("Student added successfully!");
                        ConsoleColor.printSuccess("Student saved successfully!");
                    } catch (IllegalArgumentException e) {
                        ConsoleColor.printError(e.getMessage());
                    }
                    break;
                case 2:
                    id = menuStudentManageView.inputIdStudent();
                    try {
                        student = studentRepository.findById(id);
                        if (student == null) {
                            throw new RuntimeException("This student not existed");
                        }
                    } catch (RuntimeException e) {
                        ConsoleColor.printError(e.getMessage());
                        continue;
                    }
                    menuStudentManageView.updateStudent(student);
                    studentRepository.save();
                    ConsoleColor.printSuccess("Student saved successfully!");
                    break;
                case 3:
                    id = menuStudentManageView.inputIdStudent();
                    try {
                        Student deletedStudent = studentRepository.findById(id);
                        if (deletedStudent == null) {
                            throw new RuntimeException("This student not existed");
                        }
                    } catch (RuntimeException e) {
                        ConsoleColor.printError(e.getMessage());
                        continue;
                    }
                    menuStudentManageView.displayOneStudent(students, id);
                    if (menuStudentManageView.confirmDelete()) {
                        studentRepository.deleteById(id);
                        studentRepository.save();
                        ConsoleColor.printSuccess("Student saved successfully!");
                    }
                    break;
                case 4:
                    menuStudentManageView.displayAllStudents(studentService.getAllStudents());
                    break;
                case 0:
                    return;
            }
        }
    }
}
