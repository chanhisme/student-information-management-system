package controller;

import controller.student.StudentController;
import controller.faculty.FaultyManagementController;

import model.faculty.Faculty;
import view.MenuView;
public class MainMenuController {

    private final MenuView menuView;
    private final StudentController studentController;
    private final FaultyManagementController faultyManagementController;
    public MainMenuController(MenuView menuView, StudentController studentController, FaultyManagementController faultyManagementController) {
        this.menuView = menuView;
        this.studentController = studentController;
        this.faultyManagementController = faultyManagementController;
    }

    public void run() {
        while(true){
            menuView.showMenu();
            int choice = menuView.inputChoice(0,3);
            switch (choice){
                case 1:
                    studentController.run();
                    break;
                case 3:
                    faultyManagementController.run();
                    break;
                case 0:
                    menuView.showExitMessage();
                    return;

            }
        }
    }

}