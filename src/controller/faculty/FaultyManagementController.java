package controller.faculty;

import model.faculty.Faculty;
import service.faculty.FacultyService;
import view.ConsoleColor;
import view.faculty.MenuFaultyManagementView;

public class FaultyManagementController {
    private final MenuFaultyManagementView menuFaultyManagementView;
    private final FacultyService facultyService;
    private final MajorManagementController majorManagementController;

    public FaultyManagementController(MenuFaultyManagementView menuFaultyManagementView, FacultyService facultyService,
            MajorManagementController majorManagementController) {
        this.menuFaultyManagementView = menuFaultyManagementView;
        this.facultyService = facultyService;
        this.majorManagementController = majorManagementController;
    }

    public void run() {
        while (true) {
            menuFaultyManagementView.showMenu();
            int choice = menuFaultyManagementView.inputChoice(0, 7);
            switch (choice) {
                case 1:
                    viewAllFaculties();
                    break;
                case 2:
                    addFaculty();
                    break;
                case 3:
                    updateFacultyByPreFix();
                    break;
                case 4:
                    deleteFacultyByPreFix();
                    break;
                case 5:
                    viewFacultyByPreFix();
                    break;
                case 6:
                    enterMajorManagement();
                    break;
                case 7:
                    viewFacultyDetailByPreFix();
                    break;
                case 0:
                    return;
            }
        }
    }

    private void viewAllFaculties() {
        menuFaultyManagementView.displayAllFaculties(facultyService.getAllFaculty());
    }

    private void addFaculty() {
        Faculty faculty = menuFaultyManagementView.inputFaculty();
        if (faculty == null) {
            ConsoleColor.printError("Faculty creation cancelled.");
            return;
        }
        try {
            facultyService.addFaculty(faculty);
            ConsoleColor.printSuccess("Faculty added successfully!");
            ConsoleColor.printSuccess("Faculty saved successfully!");
        } catch (IllegalArgumentException e) {
            ConsoleColor.printError(e.getMessage());
        }
    }

    private void updateFacultyByPreFix() {
        String updatePreFix = menuFaultyManagementView.inputPreFix();
        Faculty facultyToUpdate = facultyService.findByPreFix(updatePreFix);
        if (facultyToUpdate == null) {
            ConsoleColor.printError("This faculty not existed");
            return;
        }
        handleUpdateFaculty(facultyToUpdate);
    }

    private void deleteFacultyByPreFix() {
        String preFix = menuFaultyManagementView.inputPreFix();
        boolean isConfirm = menuFaultyManagementView.confirmDelete();
        if (!isConfirm) {
            return;
        }
        try {
            facultyService.deleteFaculty(preFix);
            ConsoleColor.printSuccess("Delete successfully!");
        } catch (IllegalArgumentException e) {
            ConsoleColor.printError(e.getMessage());
        }
    }

    private void viewFacultyByPreFix() {
        try {
            Faculty foundFaculty = findFacultyOrThrow(menuFaultyManagementView.inputPreFix());
            menuFaultyManagementView.displayOneFaculty(foundFaculty);
        } catch (RuntimeException e) {
            ConsoleColor.printError(e.getMessage());
        }
    }

    private void enterMajorManagement() {
        try {
            Faculty foundFaculty = findFacultyOrThrow(menuFaultyManagementView.inputPreFix());
            menuFaultyManagementView.displayOneFaculty(foundFaculty);
            majorManagementController.run(foundFaculty);
        } catch (RuntimeException e) {
            ConsoleColor.printError(e.getMessage());
        }
    }

    private void viewFacultyDetailByPreFix() {
        try {
            Faculty foundFaculty = findFacultyOrThrow(menuFaultyManagementView.inputPreFix());
            menuFaultyManagementView.displayDetailFaculty(foundFaculty);
        } catch (RuntimeException e) {
            ConsoleColor.printError(e.getMessage());
        }
    }

    private Faculty findFacultyOrThrow(String preFix) {
        Faculty foundFaculty = facultyService.findByPreFix(preFix);
        if (foundFaculty == null) {
            throw new RuntimeException("This id not existed");
        }
        return foundFaculty;
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
