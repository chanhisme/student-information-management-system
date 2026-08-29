
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
            String id;
            Subject subject;

            switch (choice) {
                case 1:
                    menuSubjectManageView.displayAllSubjects(subjectService.getAllSubjects());
                    break;

                case 2:
                    String newId = subjectService.generateSubjectId();
                    Subject newSubject = menuSubjectManageView.inputSubjectData(newId);
                    if (newSubject == null) {
                        ConsoleColor.printError("Subject creation cancelled.");
                        break;
                    }
                    try {
                        subjectService.addSubject(newSubject);
                        ConsoleColor.printSuccess("Subject added successfully!");
                    } catch (IllegalArgumentException e) {
                        ConsoleColor.printError(e.getMessage());
                    }
                    break;

                case 3:
                    id = menuSubjectManageView.inputId();
                    subject = subjectService.findById(id);
                    if (subject == null) {
                        ConsoleColor.printError("This subject not existed.");
                        break;
                    }
                    handleUpdateSubject(subject);
                    break;

                case 4:
                    id = menuSubjectManageView.inputId();
                    subject = subjectService.findById(id);
                    if (subject == null) {
                        ConsoleColor.printError("This subject not existed.");
                        break;
                    }
                    menuSubjectManageView.displayOneSubject(subject);
                    if (menuSubjectManageView.confirmDelete()) {
                        try {
                            subjectService.deleteSubject(id);
                            ConsoleColor.printSuccess("Subject deleted successfully!");
                        } catch (IllegalArgumentException e) {
                            ConsoleColor.printError(e.getMessage());
                        }
                    }
                    break;

                case 5:
                    System.out.print("Enter search query (ID or Name): ");
                    String query = new java.util.Scanner(System.in).nextLine();
                    java.util.List<Subject> searchResults = subjectService.searchSubjects(query);
                    if (searchResults.isEmpty()) {
                        ConsoleColor.printError("No subjects found.");
                    } else {
                        menuSubjectManageView.displayAllSubjects(new java.util.ArrayList<>(searchResults));
                    }
                    break;

                case 0:
                    return;
            }
        }
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