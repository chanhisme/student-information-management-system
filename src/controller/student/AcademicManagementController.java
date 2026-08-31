package controller.student;

import view.student.MenuAcademicManagementView;

public class AcademicManagementController {
    private final MenuAcademicManagementView menuAcademicManagementView;
    private final GradeManageController gradeManageController;
    public AcademicManagementController(MenuAcademicManagementView menuAcademicManagementView, GradeManageController gradeManageController) {
        this.menuAcademicManagementView = menuAcademicManagementView;
        this.gradeManageController = gradeManageController;
    }

    public void run() {
        while (true) {
            menuAcademicManagementView.showMenu();
            int choice = menuAcademicManagementView.inputChoice(0, 5);
            switch (choice) {
                case 1:
                    gradeManageController.run();
                    break;
                case 0:
                    return;
            }
        }
    }
}
