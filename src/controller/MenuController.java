package controller;

import view.MenuView;

public class MenuController {

    private final MenuView menuView;

    public MenuController(MenuView menuView) {
        this.menuView = menuView;
    }

    public void run() {
        while(true){
            menuView.showMainMenu();
            int choice = menuView.inputChoice();
            switch (choice){
                case 0:
                    menuView.showExitMessage();
                    break;
                default:
                    menuView.showInvalidChoice();
            }
        }
    }

}