package controller;

import view.MenuCourseRegistrationView;

public class CourseRegistrationController {
    private final MenuCourseRegistrationView menuCourseRegistrationView;

    public CourseRegistrationController(MenuCourseRegistrationView menuCourseRegistrationView) {
        this.menuCourseRegistrationView = menuCourseRegistrationView;
    }

    public void run() {
        int choice = 0;
        while (true) {
            menuCourseRegistrationView.showMenu();
            choice = menuCourseRegistrationView.inputChoice();
            switch (choice) {
                case 1:

                case 0:
                    return;
                default:
                    menuCourseRegistrationView.showInvalidChoice();
                    break;
            }
        }
    }
}
