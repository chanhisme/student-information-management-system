package controller;

import controller.student.StudentController;
import controller.faculty.FacultyManagementController;
import controller.subject.SubjectManageController;

import view.MenuView;
public class MainMenuController {

    private final MenuView menuView;
    private final StudentController studentController;
    private final SubjectManageController subjectManageController;
    private final FacultyManagementController facultyManagementController;

    public MainMenuController(MenuView menuView, StudentController studentController,
                              SubjectManageController subjectManageController,
                              FacultyManagementController facultyManagementController) {
        this.menuView = menuView;
        this.studentController = studentController;
        this.subjectManageController = subjectManageController;
        this.facultyManagementController = facultyManagementController;
    }

    public void run() {
        while(true){
            menuView.showMenu();
            int choice = menuView.inputChoice(0,3);
            if(choice == -1){
                return;
            }
            switch (choice){
                case 1:
                    studentController.run();
                    break;
                case 2:
                    subjectManageController.run();
                    break;
                case 3:
                    facultyManagementController.run();
                    break;
                case 0:
                    menuView.showExitMessage();
                    return;

            }
        }
    }

}