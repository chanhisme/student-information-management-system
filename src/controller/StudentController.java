package controller;

import view.MenuStudentView;
import java.util.Scanner;
public class StudentController {
    private final MenuStudentView menuStudentView;


    public StudentController(MenuStudentView menuStudentView) {
        this.menuStudentView = menuStudentView;
    }

    public void run(){

        while(true){
            menuStudentView.showMenu();
            int choice = menuStudentView.inputChoice();
            switch (choice){

                case 0:
                    break;
                default:
                    menuStudentView.showInvalidChoice();
                    break;
            }
        }
    }

}
