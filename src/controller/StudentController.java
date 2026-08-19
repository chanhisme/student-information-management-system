package controller;

import view.MenuStudentView;


public class StudentController {
    private final MenuStudentView menuStudentView;
    private final StudentManageController studentManageController;
    private final CourseRegistrationController courseRegistrationController;
    private final StudentInformationViewController studentInformationViewController;
    public StudentController(MenuStudentView menuStudentView,
                             StudentManageController studentManageController, CourseRegistrationController courseRegistrationController, StudentInformationViewController studentInformationViewController) {
        this.menuStudentView = menuStudentView;
        this.studentManageController = studentManageController;
        this.courseRegistrationController = courseRegistrationController;
        this.studentInformationViewController = studentInformationViewController;
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
                case 3:
                    studentInformationViewController.run();
                case 0:
                    return;
            }
        }

    }

}
