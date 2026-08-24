package controller.student;

import view.student.MenuStudentInformationView;

public class StudentInformationViewController {
    private final MenuStudentInformationView menuStudentInformationView;

    public StudentInformationViewController(MenuStudentInformationView menuStudentInformationView) {
        this.menuStudentInformationView = menuStudentInformationView;
    }

    public void run(){
        while(true){
            menuStudentInformationView.showMenu();
            int choice = menuStudentInformationView.inputChoice(0,5);
            switch (choice){
                case 0:
                    return;
            }
        }
    }
}
