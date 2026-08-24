package controller;

import model.Student;
import service.StudentService;
import view.ConsoleColor;
import view.MenuStudentManageView;

public class StudentManageController {
    private final MenuStudentManageView menuStudentManageView;
    private final StudentService studentService;

    public StudentManageController(MenuStudentManageView menuStudentManageView, StudentService studentService) {
        this.menuStudentManageView = menuStudentManageView;
        this.studentService = studentService;
    }

    public void run() {
        while (true) {
            menuStudentManageView.showMenu();
            int choice = menuStudentManageView.inputChoice(0, 5);
            String id;
            Student student;
            switch (choice) {

                case 1:
                    student = menuStudentManageView.inputStudentData();
                    if (student == null) {
                        ConsoleColor.printError("Student creation cancelled.");
                        break;
                    }
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
                    student = studentService.findById(id);
                    if (student == null) {
                        ConsoleColor.printError("This student not existed");
                        break;
                    }
                    menuStudentManageView.updateStudent(student);
                    try {
                        studentService.updateStudent(student);
                        ConsoleColor.printSuccess("Student saved successfully!");
                    } catch (IllegalArgumentException e) {
                        ConsoleColor.printError(e.getMessage());
                    }
                    break;
                case 3:
                    id = menuStudentManageView.inputIdStudent();
                    student = studentService.findById(id);
                    if (student == null) {
                        ConsoleColor.printError("This student not existed");
                        break;
                    }
                    menuStudentManageView.displayOneStudent(student);
                    if (menuStudentManageView.confirmDelete()) {
                        try {
                            studentService.deleteStudent(id);
                            ConsoleColor.printSuccess("Student deleted successfully!");
                        } catch (IllegalArgumentException e) {
                            ConsoleColor.printError(e.getMessage());
                        }
                    }
                    break;
                case 4:
                    menuStudentManageView.displayAllStudents(studentService.getAllStudents());
                    break;
                case 5:
                    id = menuStudentManageView.inputIdStudent();
                    student = studentService.findById(id);
                    if (student == null) {
                        ConsoleColor.printError("Student not found.");
                    } else {
                        menuStudentManageView.displayOneStudent(student);
                    }
                    break;
                case 0:
                    return;
            }
        }
    }
}
