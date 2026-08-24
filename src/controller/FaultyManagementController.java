package controller;

import view.MenuFaultyManagementView;

public class FaultyManagementController {
    private final MenuFaultyManagementView menuFaultyManagementView;

    public FaultyManagementController(MenuFaultyManagementView menuFaultyManagementView) {
        this.menuFaultyManagementView = menuFaultyManagementView;
    }

    public void run() {
        while(true){
            menuFaultyManagementView.showMenu();
            int choice = menuFaultyManagementView.inputChoice(0, 7);
            switch(choice){

                case 0:
                    return;
            }
        }
    }
}
