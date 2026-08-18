package controller;

import view.MenuView;
public class MainMenuController {

    private final MenuView menuView;
    private final StudentController studentController;
    public MainMenuController(MenuView menuView, StudentController studentController) {
        this.menuView = menuView;
        this.studentController = studentController;
    }

    public void run() {
        while(true){
            menuView.showMenu();
            int choice = menuView.inputChoice(0,3);
            switch (choice){
                case 1:
                    studentController.run();
                    break;

                case 0:
                    menuView.showExitMessage();
                    return;

            }
        }
    }

}