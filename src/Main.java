import controller.*;
import view.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        MenuView menuView = new MenuView(scanner);
        MenuStudentView menuStudentView = new MenuStudentView(scanner);
        MenuStudentManageView menuStudentManageView = new MenuStudentManageView(scanner);
        MenuCourseRegistrationView menuCourseRegistrationView = new MenuCourseRegistrationView(scanner);
        MenuStudentInformationView menuStudentInformationView = new MenuStudentInformationView(scanner);
        StudentInformationViewController studentInformationViewController = new StudentInformationViewController(menuStudentInformationView);

        CourseRegistrationController courseRegistrationController = new CourseRegistrationController(menuCourseRegistrationView);
        StudentManageController studentManageController = new StudentManageController(menuStudentManageView);
        StudentController studentController = new StudentController(menuStudentView,
                studentManageController, courseRegistrationController, studentInformationViewController);


        MainMenuController mainMenuController = new MainMenuController(menuView, studentController);


        mainMenuController.run();
    }
}