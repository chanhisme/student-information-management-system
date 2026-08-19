package controller;

import view.MenuAcademicManagementView;

public class AcademicManagementController {
    private final MenuAcademicManagementView menuAcademicManagementView;

    public AcademicManagementController(MenuAcademicManagementView menuAcademicManagementView) {
        this.menuAcademicManagementView = menuAcademicManagementView;
    }
    public void run(){
        while(true){
            menuAcademicManagementView.showMenu();
            int choice = menuAcademicManagementView.inputChoice(0,5);
            switch (choice){
                case 0:
                    return;
            }
        }
    }
}
