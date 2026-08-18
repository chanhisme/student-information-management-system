package controller;

import view.MenuStudentView;


public class StudentController {
    private final MenuStudentView menuStudentView;
    private final StudentManageController studentManageController;
    private final CourseRegistrationController courseRegistrationController;
    public StudentController(MenuStudentView menuStudentView,
                             StudentManageController studentManageController, CourseRegistrationController courseRegistrationController) {
        this.menuStudentView = menuStudentView;
        this.studentManageController = studentManageController;
        this.courseRegistrationController = courseRegistrationController;
    }

    public void run() {
        int choice = 0;
        while(true){
            menuStudentView.showMenu();
            choice = menuStudentView.inputChoice(0,5);
            switch (choice) {
                case 1:
                    studentManageController.run();
                    break;
                case 2:
                    courseRegistrationController.run();
                    break;
                case 0:
                    return;
            }
        }

    }

}
