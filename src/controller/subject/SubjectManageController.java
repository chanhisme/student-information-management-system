package controller.subject;

import model.subject.Subject;
import service.subject.SubjectService;
import view.ConsoleColor;
import view.subject.MenuSubjectManageView;

public class SubjectManageController {

    private final MenuSubjectManageView menuSubjectManageView;
    private final SubjectService subjectService;

    public SubjectManageController(MenuSubjectManageView menuSubjectManageView,
                                   SubjectService subjectService) {
        this.menuSubjectManageView = menuSubjectManageView;
        this.subjectService = subjectService;
    }

    public void run() {
        while (true) {
            menuSubjectManageView.showMenu();
            int choice = menuSubjectManageView.inputChoice(0, 5);
            switch (choice) {
                case 1:
                    viewAllSubjects();
                    break;
                case 2:
                    addSubject();
                    break;
                case 3:
                    updateSubjectById();
                    break;
                case 4:
                    deleteSubjectById();
                    break;
                case 5:
                    searchSubjects();
                    break;
                case 0:
                    return;
            }
        }
    }

    private void viewAllSubjects() {
        menuSubjectManageView.displayAllSubjects(subjectService.getAllSubjects());
    }

    private void addSubject() {
        String newId = subjectService.generateSubjectId();
        Subject newSubject = menuSubjectManageView.inputSubjectData(newId);
        if (newSubject == null) {
            ConsoleColor.printError("Subject creation cancelled.");
            return;
        }
        try {
            subjectService.addSubject(newSubject);
            ConsoleColor.printSuccess("Subject added successfully!");
        } catch (IllegalArgumentException e) {
            ConsoleColor.printError(e.getMessage());
        }
    }

    private void updateSubjectById() {
        try {
            Subject subject = findSubjectOrThrow(menuSubjectManageView.inputId());
            handleUpdateSubject(subject);
        } catch (RuntimeException e) {
            ConsoleColor.printError(e.getMessage());
        }
    }

    private void deleteSubjectById() {
        String id = menuSubjectManageView.inputId();
        try {
            Subject subject = findSubjectOrThrow(id);
            menuSubjectManageView.displayOneSubject(subject);
            if (!menuSubjectManageView.confirmDelete()) {
                return;
            }
            try {
                subjectService.deleteSubject(id);
                ConsoleColor.printSuccess("Subject deleted successfully!");
            } catch (IllegalArgumentException e) {
                ConsoleColor.printError(e.getMessage());
            }
        } catch (RuntimeException e) {
            ConsoleColor.printError(e.getMessage());
        }
    }

    private void searchSubjects() {
        System.out.print("Enter search query (ID or Name): ");
        String query = new java.util.Scanner(System.in).nextLine();
        java.util.List<Subject> searchResults = subjectService.searchSubjects(query);
        if (searchResults.isEmpty()) {
            ConsoleColor.printError("No subjects found.");
        } else {
            menuSubjectManageView.displayAllSubjects(new java.util.ArrayList<>(searchResults));
        }
    }

    private Subject findSubjectOrThrow(String id) {
        Subject subject = subjectService.findById(id);
        if (subject == null) {
            throw new RuntimeException("This subject does not exist.");
        }
        return subject;
    }

    private void handleUpdateSubject(Subject subject) {
        boolean updating = true;
        while (updating) {
            int choice = menuSubjectManageView.displayUpdateMenuAndGetChoice(subject);
            switch (choice) {
                case 1:
                    String newName = menuSubjectManageView.inputName();
                    subject.setName(newName);
                    ConsoleColor.printSuccess("Updated name successfully.");
                    break;
                case 2:
                    int newCredits = menuSubjectManageView.inputCredits();
                    subject.setCredits(newCredits);
                    ConsoleColor.printSuccess("Updated credits successfully.");
                    break;
                case 3:
                    ConsoleColor.printError("Cannot change subject type.");
                    break;
                case 0:
                    updating = false;
                    break;
            }
            if (choice != 0 && choice != 3) {
                try {
                    subjectService.updateSubject(subject);
                    ConsoleColor.printSuccess("Subject saved successfully!");
                } catch (IllegalArgumentException e) {
                    ConsoleColor.printError(e.getMessage());
                }
            }
        }
    }
}
