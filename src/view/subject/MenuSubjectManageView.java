package view.subject;

import model.subject.*;
import view.BaseMenuView;
import view.ConsoleColor;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuSubjectManageView extends BaseMenuView {

    public MenuSubjectManageView(Scanner scanner) {
        super(scanner);
    }

    @Override
    public void showMenu() {
        System.out.println("\n==========SUBJECT MANAGEMENT==========");
        System.out.println("1. View all subjects");
        System.out.println("2. Add subject");
        System.out.println("3. Update subject");
        System.out.println("4. Delete subject");
        System.out.println("5. Search subject");
        System.out.println("0. Back");
    }

    public Subject inputSubjectData(String id) {
        System.out.println("\n==========Enter Subject Details==========");
        String name = inputName();
        int credits = inputCredits();
        String type = inputSubjectType();
        return Subject.create(type, id, name, credits);
    }

    public String inputId() {
        while (true) {
            System.out.print("Enter Subject ID: ");
            String id = scanner.nextLine().trim().toUpperCase();
            if (id.isEmpty()) {
                ConsoleColor.printError("Subject ID cannot be empty.");
                continue;
            }
            if (!id.matches("SUB[1-9][0-9]*")) {
                ConsoleColor.printError("Subject ID must be SUB + number, e.g. SUB1.");
                continue;
            }
            return id;
        }
    }

    public String inputName() {
        while (true) {
            System.out.print("Enter Subject Name: ");
            String name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                ConsoleColor.printError("Subject name cannot be empty.");
                continue;
            }
            if (!name.matches("[\\p{L}0-9 ]+")) {
                ConsoleColor.printError("Subject name can only contain letters, numbers and spaces.");
                continue;
            }
            return name;
        }
    }

    public int inputCredits() {
        while (true) {
            try {
                System.out.print("Enter Credits (1-10): ");
                int credits = Integer.parseInt(scanner.nextLine().trim());
                if (credits < 1 || credits > 10) {
                    ConsoleColor.printError("Credits must be between 1 and 10.");
                    continue;
                }
                return credits;
            } catch (NumberFormatException e) {
                ConsoleColor.printError("Please enter a valid number.");
            }
        }
    }

    public String inputSubjectType() {
        System.out.println("\n==========SUBJECT TYPE==========");
        String[] types = Subject.getRegisteredTypes().toArray(new String[0]);
        for (int i = 0; i < types.length; i++) {
            System.out.println((i + 1) + ". " + types[i]);
        }
        while (true) {
            try {
                System.out.print("Enter choice: ");
                int choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice >= 1 && choice <= types.length) {
                    return types[choice - 1];
                }
                ConsoleColor.printError("Invalid choice.");
            } catch (NumberFormatException e) {
                ConsoleColor.printError("Please enter a valid number.");
            }
        }
    }

    public boolean confirmDelete() {
        System.out.println("Confirm deletion of this subject?");
        System.out.println("[1] YES");
        System.out.println("[2] NO");
        int choice = inputChoice(1, 2);
        return choice == 1;
    }

    public int displayUpdateMenuAndGetChoice(Subject subject) {
        System.out.println("\n=============UPDATE SUBJECT=============");
        System.out.println("[1]. Name: " + subject.getName());
        System.out.println("[2]. Credits: " + subject.getCredits());
        System.out.println("[3]. Type: " + getSubjectTypeName(subject));
        System.out.println("[0]. DONE");
        return inputChoice(0, 3);
    }

    public void displayAllSubjects(ArrayList<Subject> subjects) {
        if (subjects == null || subjects.isEmpty()) {
            System.out.println("\nNo subjects found.");
            return;
        }

        System.out.println();
        System.out.println("================================== SUBJECT LIST ==================================");
        System.out.printf("%-10s | %-25s | %-8s | %-12s%n",
                "ID", "Name", "Credits", "Type");
        System.out.println("---------------------------------------------------------------------------------");

        for (Subject subject : subjects) {
            System.out.printf("%-10s | %-25s | %-8d | %-12s%n",
                    subject.getId(),
                    subject.getName(),
                    subject.getCredits(),
                    getSubjectTypeName(subject));
        }

        System.out.println("=================================================================================");
        System.out.println("Total: " + subjects.size() + " subjects");
    }

    public void displayOneSubject(Subject subject) {
        if (subject == null) {
            System.out.println("\nSubject not found.");
            return;
        }
        System.out.println("\n========== SUBJECT DETAILS ==========");
        System.out.println("ID    : " + subject.getId());
        System.out.println("Name  : " + subject.getName());
        System.out.println("Credits: " + subject.getCredits());
        System.out.println("Type  : " + getSubjectTypeName(subject));
        System.out.println("GPA   : " + (subject.isCalculatedGpa() ? "Yes" : "No"));
    }

    private String getSubjectTypeName(Subject subject) {
        return subject.getType();
    }
}
