package controller;

import view.MenuStudentView;

import java.util.Scanner;

public class StudentController {
    private final MenuStudentView menuStudentView;
    private final StudentManageController studentManageController;

    public StudentController(MenuStudentView menuStudentView,
                             StudentManageController studentManageController) {
        this.menuStudentView = menuStudentView;
        this.studentManageController = studentManageController;
    }

    public void run() {
        int choice = 0;
        while(true){
            menuStudentView.showMenu();
            choice = menuStudentView.inputChoice();
            switch (choice) {
                case 1:
                    studentManageController.run();
                    break;
                case 0:
                    return;
                default:
                    menuStudentView.showInvalidChoice();
                    break;
            }
        }

    }

}
