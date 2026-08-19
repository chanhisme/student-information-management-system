import controller.*;
import view.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Initialize Views
        MenuView menuView = new MenuView(scanner);
        MenuStudentView menuStudentView = new MenuStudentView(scanner);
        MenuStudentManageView menuStudentManageView = new MenuStudentManageView(scanner);
        MenuCourseRegistrationView menuCourseRegistrationView = new MenuCourseRegistrationView(scanner);
        MenuStudentInformationView menuStudentInformationView = new MenuStudentInformationView(scanner);
        MenuAcademicManagementView menuAcademicManagementView = new MenuAcademicManagementView(scanner);
        MenuGraduationProgressView menuGraduationProgressView = new MenuGraduationProgressView(scanner);

        // 2. Initialize Sub-Controllers
        StudentManageController studentManageController = new StudentManageController(menuStudentManageView);
        CourseRegistrationController courseRegistrationController = new CourseRegistrationController(menuCourseRegistrationView);
        StudentInformationViewController studentInformationViewController = new StudentInformationViewController(menuStudentInformationView);
        AcademicManagementController academicManagementController = new AcademicManagementController(menuAcademicManagementView);
        GraduationProgressController graduationProgressController = new GraduationProgressController(menuGraduationProgressView);

        // 3. Initialize Feature Controllers
        StudentController studentController = new StudentController(
                menuStudentView,
                studentManageController,
                courseRegistrationController,
                studentInformationViewController,
                academicManagementController,
                graduationProgressController
        );

        // 4. Initialize Main Menu Controller & Run
        MainMenuController mainMenuController = new MainMenuController(menuView, studentController);
        mainMenuController.run();
    }
}