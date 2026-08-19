package controller;

import view.MenuStudentView;


public class StudentController {
    private final MenuStudentView menuStudentView;
    private final StudentManageController studentManageController;
    private final CourseRegistrationController courseRegistrationController;
    private final StudentInformationViewController studentInformationViewController;
    private final AcademicManagementController academicManagementController;
    private final GraduationProgressController graduationProgressController;


    public StudentController(MenuStudentView menuStudentView,
                             StudentManageController studentManageController, CourseRegistrationController courseRegistrationController, StudentInformationViewController studentInformationViewController, AcademicManagementController academicManagementController, GraduationProgressController graduationProgressController) {
        this.menuStudentView = menuStudentView;
        this.studentManageController = studentManageController;
        this.courseRegistrationController = courseRegistrationController;
        this.studentInformationViewController = studentInformationViewController;
        this.academicManagementController = academicManagementController;
        this.graduationProgressController = graduationProgressController;
    }

    public void run() {
        int choice = 0;
        while (true) {
            menuStudentView.showMenu();
            choice = menuStudentView.inputChoice(0, 5);
            switch (choice) {
                case 1:
                    studentManageController.run();
                    break;
                case 2:
                    courseRegistrationController.run();
                    break;
                case 3:
                    studentInformationViewController.run();
                    break;
                case 4:
                    academicManagementController.run();
                    break;
                case 5:
                    graduationProgressController.run();
                    break;
                case 0:
                    return;
            }
        }

    }

}
