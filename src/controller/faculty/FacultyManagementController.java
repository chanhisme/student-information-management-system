package controller.faculty;

import model.faculty.Faculty;
import service.faculty.FacultyService;
import view.ConsoleColor;
import view.faculty.MenuFacultyManagementView;

public class FacultyManagementController {
    private final MenuFacultyManagementView menuFacultyManagementView;
    private final FacultyService facultyService;
    private final MajorManagementController majorManagementController;

    public FacultyManagementController(MenuFacultyManagementView menuFacultyManagementView, FacultyService facultyService,
                                       MajorManagementController majorManagementController) {
        this.menuFacultyManagementView = menuFacultyManagementView;
        this.facultyService = facultyService;
        this.majorManagementController = majorManagementController;
    }

    public void run() {
        while (true) {
            menuFacultyManagementView.showMenu();
            int choice = menuFacultyManagementView.inputChoice(0, 7);
            if (choice == -1) {
                return;
            }
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
        menuFacultyManagementView.displayAllFaculties(facultyService.getAllFaculty());
    }

    private void addFaculty() {


        try {
            Faculty faculty = menuFacultyManagementView.inputFaculty();
            if (faculty == null) {
                ConsoleColor.printError("Faculty creation cancelled.");
                return;
            }
            facultyService.addFaculty(faculty);
            ConsoleColor.printSuccess("Faculty added successfully!");
        } catch (IllegalArgumentException e) {
            ConsoleColor.printError(e.getMessage());
        }
    }

    private void updateFacultyByPreFix() {
        String updatePreFix = menuFacultyManagementView.inputPreFix();
        if (updatePreFix == null) {
            return;
        }
        Faculty facultyToUpdate = facultyService.findByPreFix(updatePreFix);
        if (facultyToUpdate == null) {
            ConsoleColor.printError("This faculty does not exist");
            return;
        }
        handleUpdateFaculty(facultyToUpdate);
    }

    private void deleteFacultyByPreFix() {
        String preFix = menuFacultyManagementView.inputPreFix();
        if (preFix == null) {
            return;
        }
        boolean isConfirm = menuFacultyManagementView.confirmDelete();
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
            String preFix = menuFacultyManagementView.inputPreFix();
            if (preFix == null) {
                return;
            }
            Faculty foundFaculty = findFacultyOrThrow(preFix);
            menuFacultyManagementView.displayOneFaculty(foundFaculty);
        } catch (RuntimeException e) {
            ConsoleColor.printError(e.getMessage());
        }
    }

    private void enterMajorManagement() {
        try {
            String preFix = menuFacultyManagementView.inputPreFix();
            if (preFix == null) {
                return;
            }
            Faculty foundFaculty = findFacultyOrThrow(preFix);
            menuFacultyManagementView.displayOneFaculty(foundFaculty);
            majorManagementController.run(foundFaculty);
        } catch (RuntimeException e) {
            ConsoleColor.printError(e.getMessage());
        }
    }

    private void viewFacultyDetailByPreFix() {
        try {
            String preFix = menuFacultyManagementView.inputPreFix();
            if (preFix == null) {
                return;
            }
            Faculty foundFaculty = findFacultyOrThrow(preFix);
            menuFacultyManagementView.displayDetailFaculty(foundFaculty);
        } catch (RuntimeException e) {
            ConsoleColor.printError(e.getMessage());
        }
    }

    private Faculty findFacultyOrThrow(String preFix) {
        Faculty foundFaculty = facultyService.findByPreFix(preFix);
        if (foundFaculty == null) {
            throw new RuntimeException("This ID does not exist");
        }
        return foundFaculty;
    }

    private void handleUpdateFaculty(Faculty faculty) {
        boolean updating = true;
        while (updating) {
            int choice = menuFacultyManagementView.displayUpdateMenuAndGetChoice(faculty);
            if (choice == -1) {
                return;
            }
            switch (choice) {
                case 1:
                    String newName = menuFacultyManagementView.inputFacultyName();
                    if (newName == null) {
                        break;
                    }
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
