package controller;

import view.MenuStudentView;
import view.MenuView;

import java.awt.*;

public class MenuController {

    private final MenuView menuView;
    private final MenuStudentView menuStudentView;
    public MenuController(MenuView menuView, MenuStudentView menuStudentView) {
        this.menuView = menuView;
        this.menuStudentView = menuStudentView;
    }

    public void run() {
        while(true){
            menuView.showMenu();
            int choice = menuView.inputChoice();
            switch (choice){
                case 1:
                    menuStudentView.showMenu();
                    int studentChoice = menuStudentView.inputChoice();

                case 0:
                    menuView.showExitMessage();
                    break;
                default:
                    menuView.showInvalidChoice();
            }
        }
    }

}