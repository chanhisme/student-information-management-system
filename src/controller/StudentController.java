package controller;

import view.MenuStudentView;

import java.util.Scanner;

public class StudentController {
    private final MenuStudentView menuStudentView;


    public StudentController(MenuStudentView menuStudentView) {
        this.menuStudentView = menuStudentView;
    }

    public void run() {
        int choice = 0;
        while(true){
            menuStudentView.showMenu();
            choice = menuStudentView.inputChoice();
            switch (choice) {
                case 1:

                case 0:
                    return;
                default:
                    menuStudentView.showInvalidChoice();
                    break;
            }
        }

    }

}
