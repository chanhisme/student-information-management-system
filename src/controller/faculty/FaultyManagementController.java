package controller.faculty;

import model.faculty.Faculty;
import service.faculty.FacultyService;
import view.ConsoleColor;
import view.faculty.MenuFaultyManagementView;

public class FaultyManagementController {
    private final MenuFaultyManagementView menuFaultyManagementView;
    private final FacultyService facultyService;

    public FaultyManagementController(MenuFaultyManagementView menuFaultyManagementView, FacultyService facultyService) {
        this.menuFaultyManagementView = menuFaultyManagementView;
        this.facultyService = facultyService;
    }

    public void run() {
        while (true) {
            menuFaultyManagementView.showMenu();
            int choice = menuFaultyManagementView.inputChoice(0, 7);
            switch (choice) {
                case 1:
                    menuFaultyManagementView.displayAllFaculties(facultyService.getAllFaculty());
                    break;
                case 2:
                    Faculty faculty = menuFaultyManagementView.inputFaculty();
                    if (faculty == null) {
                        ConsoleColor.printError("Faculty creation cancelled.");
                        break;
                    }
                    try {
                        facultyService.addFaculty(faculty);
                        ConsoleColor.printSuccess("Faculty added successfully!");
                        ConsoleColor.printSuccess("Faculty saved successfully!");

                    } catch (IllegalArgumentException e) {
                        ConsoleColor.printError(e.getMessage());
                    }
                    break;
                case 3:
                    String updatePreFix = menuFaultyManagementView.inputPreFix();
                    Faculty facultyToUpdate = facultyService.findByPreFix(updatePreFix);
                    if (facultyToUpdate == null) {
                        ConsoleColor.printError("This faculty not existed");
                        break;
                    }
                    handleUpdateFaculty(facultyToUpdate);
                    break;
                case 4:
                    String preFix = menuFaultyManagementView.inputPreFix();
                    boolean isConfirm = menuFaultyManagementView.confirmDelete();
                    if (isConfirm) {
                        try {
                            facultyService.deleteFaculty(preFix);
                            ConsoleColor.printSuccess("Delete successfully!");
                        } catch (IllegalArgumentException e) {
                            ConsoleColor.printError(e.getMessage());
                        }
                    }
                    break;
                case 0:
                    return;
            }
        }
    }

    private void handleUpdateFaculty(Faculty faculty) {
        boolean updating = true;
        while (updating) {
            int choice = menuFaultyManagementView.displayUpdateMenuAndGetChoice(faculty);
            switch (choice) {
                case 1:
                    String newName = menuFaultyManagementView.inputFacultyName();
                    faculty.setName(newName);
                    ConsoleColor.printSuccess("Updated name successfully.");
                    break;
                case 0:
                    updating = false;
                    break;
            }

            if (choice != 0) {
                try {
                    facultyService.updateFaculty(faculty);
                    ConsoleColor.printSuccess("Faculty saved successfully!");
                } catch (IllegalArgumentException e) {
                    ConsoleColor.printError(e.getMessage());
                }
            }
        }
    }
}
