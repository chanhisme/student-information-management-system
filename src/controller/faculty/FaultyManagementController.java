package controller.faculty;

import service.faculty.FacultyService;
import view.faculty.MenuFaultyManagementView;

public class FaultyManagementController {
    private final MenuFaultyManagementView menuFaultyManagementView;
    private final FacultyService facultyService;
    public FaultyManagementController(MenuFaultyManagementView menuFaultyManagementView, FacultyService facultyService) {
        this.menuFaultyManagementView = menuFaultyManagementView;
        this.facultyService = facultyService;
    }

    public void run() {
        while(true){
            menuFaultyManagementView.showMenu();
            int choice = menuFaultyManagementView.inputChoice(0, 7);
            switch(choice){
                case 1:
                    menuFaultyManagementView.displayAllFaculties(facultyService.getAllFaculty());
                    break;
                case 0:
                    return;
            }
        }
    }
}
