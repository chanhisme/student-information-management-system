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
            int choice = menuStudentManageView.inputChoice(0,5);
            switch (choice){
                case 0:
                    return;
            }
        }
    }

}
