package controller;

import view.MenuStudentManageView;

public class StudentManageController {
    private final MenuStudentManageView menuStudentManageView;

    public StudentManageController(MenuStudentManageView menuStudentManageView) {
        this.menuStudentManageView = menuStudentManageView;
    }
    public void run  (){
        while(true){
            menuStudentManageView.showMenu();
            int choice = menuStudentManageView.inputChoice();
            switch (choice){

                case 0:
                    return;
                default:
                    menuStudentManageView.showInvalidInput();
                    break;
            }
        }
    }

}
