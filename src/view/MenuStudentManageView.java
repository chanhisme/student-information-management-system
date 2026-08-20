package view;

import model.Faculty;
import model.Major;
import model.Student;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuStudentManageView extends BaseMenuView {
    private final ArrayList<Faculty> faculties;

    public MenuStudentManageView(Scanner scanner, ArrayList<Faculty> faculties) {
        super(scanner);
        this.faculties = faculties;
    }

    @Override
    public void showMenu() {
        System.out.println("\n========== STUDENT MANAGEMENT ==========");
        System.out.println("1. Add Student");
        System.out.println("2. Update Student");
        System.out.println("3. Delete Student");
        System.out.println("4. View All Students");
        System.out.println("0. Back");
        System.out.println("========================================");
    }

    public Student inputStudentData() {
        System.out.println("\n--- Enter Student Details ---");

        String id = inputId();
        String name = inputName();

        Faculty faculty = inputFaculty();

        if (faculty == null) {
            return null;
        }

        Major major = inputMajor(faculty.getMajors());

        if (major == null) {
            return null;
        }

        LocalDate birth = inputBirthDate();

        return new Student(name, id, major, birth, faculty);
    }

    public Major inputMajor(ArrayList<Major> majors) {
        final int PAGE_SIZE = 6;
        int currentPage = 0;

        while (true) {
            int totalPages = (majors.size() + PAGE_SIZE - 1) / PAGE_SIZE;

            System.out.println("\n========== MAJOR ==========");

            int start = currentPage * PAGE_SIZE;
            int end = Math.min(start + PAGE_SIZE, majors.size());

            for (int i = start; i < end; i++) {
                System.out.println((i - start + 1) + ". " + majors.get(i).getName());
            }

            System.out.println("----------------------------");

            if (currentPage > 0) {
                System.out.println("P. Previous page");
            }

            if (currentPage < totalPages - 1) {
                System.out.println("N. Next page");
            }

            System.out.println("0. Back");

            System.out.print("Enter choice: ");
            String choice = scanner.nextLine().trim();

            if (choice.equalsIgnoreCase("N") && currentPage < totalPages - 1) {
                currentPage++;
                continue;
            }

            if (choice.equalsIgnoreCase("P") && currentPage > 0) {
                currentPage--;
                continue;
            }

            if (choice.equals("0")) {
                return null;
            }

            try {
                int selected = Integer.parseInt(choice);

                if (selected >= 1 && selected <= end - start) {
                    return majors.get(start + selected - 1);
                }

                ConsoleColor.printError("Invalid major choice.");

            } catch (NumberFormatException e) {
                ConsoleColor.printError("Please enter a valid choice.");
            }
        }
    }

    public Faculty inputFaculty() {
        while (true) {
            System.out.println("\n========== FACULTY ==========");

            for (int i = 0; i < faculties.size(); i++) {
                System.out.println((i + 1) + ". " + faculties.get(i).getName());
            }

            System.out.println("0. Back");
            System.out.print("Enter choice: ");

            String choice = scanner.nextLine().trim();

            if (choice.equals("0")) {
                return null;
            }

            try {
                int selected = Integer.parseInt(choice);

                if (selected >= 1 && selected <= faculties.size()) {
                    return faculties.get(selected - 1);
                }

                ConsoleColor.printError("Invalid faculty choice.");

            } catch (NumberFormatException e) {
                ConsoleColor.printError("Please enter a valid choice.");
            }
        }
    }

    public String inputId() {
        while (true) {
            System.out.print("Enter Student ID: ");
            String id = scanner.nextLine().trim();
            if (id.isEmpty()) {
                ConsoleColor.printError("Student ID cannot be empty.");
                continue;
            }
            return id;
        }
    }

    public String inputName() {
        while (true) {
            System.out.print("Enter Student Name: ");
            String name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                ConsoleColor.printError("Student name cannot be empty.");
                continue;
            }
            if (!name.matches("[\\p{L} ]+")) {
                ConsoleColor.printError("Student name can only contain letters and spaces.");
                continue;
            }
            return name;
        }
    }


    public LocalDate inputBirthDate() {
        while (true) {
            try {
                System.out.print("Enter Birth Date (dd/MM/yyyy): ");
                String birthInput = scanner.nextLine().trim();
                return LocalDate.parse(birthInput, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            } catch (DateTimeParseException e) {
                ConsoleColor.printError("Invalid date. Please use dd/MM/yyyy.");
            }
        }
    }
}
