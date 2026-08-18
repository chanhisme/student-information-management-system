package controller;

import model.Student;
import view.MenuView;
public class MenuController {

    private final MenuView menuView;
    private final StudentController studentController;
    public MenuController(MenuView menuView, StudentController studentController) {
        this.menuView = menuView;
        this.studentController = studentController;
    }

    public void run() {
        while(true){
            menuView.showMenu();
            int choice = menuView.inputChoice();
            switch (choice){
                case 1:
                    studentController.run();
                    break;

                case 0:
                    menuView.showExitMessage();
                    break;
                default:
                    menuView.showInvalidChoice();

            }
        }
    }

}