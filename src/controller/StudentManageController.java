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
            int choice = menuStudentManageView.inputChoice(0, 4);
            switch (choice) {
                case 1:
                    Student student = menuStudentManageView.inputStudentData();
                    studentService.addStudent(student);
                    ConsoleColor.printSuccess("Student added successfully!");
                    break;
                case 0:
                    return;
            }
        }
    }
}
