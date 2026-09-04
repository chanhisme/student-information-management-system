package view.student;

import model.faculty.Faculty;
import model.faculty.Major;
import model.student.Student;
import view.BaseMenuView;
import view.ConsoleColor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuStudentManageView extends BaseMenuView {
    private final ArrayList<Faculty> faculties;
    public static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);

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
        System.out.println("5. Find Student");
        System.out.println("6. Sort Students");
        System.out.println("0. Back");
        System.out.println("========================================");
    }

    public int displaySortMenuAndGetChoice() {
        System.out.println("\n========== SORT STUDENTS ==========");
        System.out.println("1. Sort by Name (A-Z)");
        System.out.println("2. Sort by GPA (Descending)");
        System.out.println("3. Sort by GPA (Ascending)");
        System.out.println("0. Back");
        return inputChoice(0, 3);
    }

    public int displayUpdateMenuAndGetChoice(Student student) {
        System.out.println("\n============= UPDATE INFORMATION =============");
        System.out.println("[1]. Name: " + student.getName());
        System.out.println("[2]. Date of Birth: " + (
                student.getBirth() != null ? student.getBirth().format(DATE_FORMATTER) : "N/A"));
        System.out.println("[3]. Faculty " + (student.getFaculty() != null ? student.getFaculty().getName() : "N/A"));
        System.out.println("[4]. Major only: " + (student.getMajor() != null ? student.getMajor().getName() : "N/A"));
        System.out.println("[5]. Status: " + student.getStatus());
        System.out.println("[0]. DONE");
        return inputChoice(0, 5);
    }

    public Student.StudentStatus inputStatus() {
        System.out.println("\n========== SELECT STATUS ==========");
        Student.StudentStatus[] statuses = Student.StudentStatus.values();
        for (int i = 0; i < statuses.length; i++) {
            System.out.println((i + 1) + ". " + statuses[i]);
        }
        System.out.println("0. Back");
        int statusChoice = inputChoice(0, statuses.length);
        if (statusChoice > 0) {
            return statuses[statusChoice - 1];
        }
        return null;
    }


    public String inputIdStudent() {
        System.out.print("Enter student id (Q to cancel): ");
        String line = readLineOrNull();
        if (line == null) {
            return null;
        }
        return normalizeInput(line).toUpperCase();
    }

    public boolean confirmDelete() {

        System.out.println("Confirm deletion of this student?");
        System.out.println("[1] YES");
        System.out.println("[2] NO");
        System.out.print("Enter your choice: ");

        int choice = inputChoice(1, 2);
        return choice == 1;
    }


    public int inputSearchingChoice(){
        System.out.println("\n====SEARCHING====");
        System.out.println("[1]. Id");
        System.out.println("[2]. Name");
        return inputChoice(1,2);
    }
    public Student inputStudentData() {
        System.out.println("\n--- Enter Student Details (Q to cancel) ---");
        Faculty faculty = inputFaculty();
        if (faculty == null) {
            return null;
        }

        Major major = inputMajor(faculty.getMajors());


        if (major == null) {
            return null;
        }

        String id = inputId(major.getId());
        if (id == null) {
            return null;
        }
        String name = inputName();
        if (name == null) {
            return null;
        }


        LocalDate birth = inputBirthDate();
        if (birth == null) {
            return null;
        }

        return new Student(name, id, major, birth, faculty);
    }

    public Major inputMajor(ArrayList<Major> majors) {
        final int PAGE_SIZE = 6;
        int currentPage = 0;

        while (true) {
            int totalPages = majors.size() / PAGE_SIZE;
            if (majors.size() % PAGE_SIZE != 0) {
                ++totalPages;
            }

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

            System.out.print("Enter choice (number / N / P / Q to cancel): ");
            String choice = readLineOrNull();
            if (choice == null) {
                return null;
            }
            choice = choice.trim();

            if (choice.equalsIgnoreCase("N") && currentPage < totalPages - 1) {
                currentPage++;
                continue;
            }

            if (choice.equalsIgnoreCase("P") && currentPage > 0) {
                currentPage--;
                continue;
            }

            if (choice.equalsIgnoreCase("0")) {
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
            System.out.print("Enter choice (Q to cancel): ");

            String line = readLineOrNull();
            if (line == null) {
                return null;
            }
            try {
                int choice = Integer.parseInt(line.trim());

                if (choice == 0) {
                    return null;
                }

                if (choice >= 1 && choice <= faculties.size()) {
                    return faculties.get(choice - 1);
                }

                ConsoleColor.printError("Invalid faculty choice.");

            } catch (NumberFormatException e) {
                ConsoleColor.printError("Please enter a valid choice.");
            }
        }
    }

    public String inputId(String preFixId) {
        while (true) {
            System.out.print("Enter Student ID (Q to cancel): ");
            String line = readLineOrNull();
            if (line == null) {
                return null;
            }
            String id = line.trim();
            if (id.isEmpty()) {
                ConsoleColor.printError("Student ID cannot be empty.");
                continue;
            }
            if (!id.toUpperCase().startsWith(preFixId.toUpperCase())) {
                ConsoleColor.printError("Student ID must start with the correct prefix");
                continue;
            }
            return id.toUpperCase();
        }
    }

    public String inputName() {
        while (true) {
            System.out.print("Enter Student Name (Q to cancel): ");
            String line = readLineOrNull();
            if (line == null) {
                return null;
            }
            String name = line.trim();
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
            System.out.print("Enter Birth Date (dd/MM/yyyy, Q to cancel): ");
            String birthInput = readLineOrNull();
            if (birthInput == null) {
                return null;
            }
            birthInput = birthInput.trim();
            try {
                return LocalDate.parse(birthInput, DATE_FORMATTER);
            } catch (DateTimeParseException e) {
                ConsoleColor.printError("Invalid date. Please use day/month/year (dd/MM/yyyy), e.g. 25/12/2006.");
            }
        }
    }

    public void displayAllStudents(List<Student> students) {
        if (students == null || students.isEmpty()) {
            System.out.println("\nNo students found.");
            return;
        }


        System.out.println();
        System.out.println("================================================= STUDENT LIST =================================================");
        System.out.printf(
                "%-12s | %-22s | %-32s | %-10s | %-12s | %-10s%n",
                "ID", "Name", "Major", "Faculty", "Birth Date", "Status"
        );
        System.out.println("----------------------------------------------------------------------------------------------------------------");

        for (Student student : students) {
            String majorName = (student.getMajor() != null) ? student.getMajor().getName() : "N/A";
            String facultyPrefix = (student.getFaculty() != null) ? student.getFaculty().getPrefix() : "N/A";
            String formattedBirthDate = (student.getBirth() != null) ? student.getBirth().format(DATE_FORMATTER) : "N/A";

            System.out.printf(
                    "%-12s | %-22s | %-32s | %-10s | %-12s | %-10s%n",
                    student.getId(),
                    student.getName(),
                    majorName,
                    facultyPrefix,
                    formattedBirthDate,
                    student.getStatus()
            );
        }

        System.out.println("================================================================================================================");
        System.out.println();
    }

    public void displayAllStudentsSorted(java.util.List<Student> students) {
        if (students == null || students.isEmpty()) {
            System.out.println("\nNo students found.");
            return;
        }

        System.out.println();
        System.out.println("================================================= SORTED STUDENT LIST =================================================");
        System.out.printf(
                "%-12s | %-22s | %-32s | %-10s | %-12s | %-10s | %-5s%n",
                "ID", "Name", "Major", "Faculty", "Birth Date", "Status", "GPA"
        );
        System.out.println("-------------------------------------------------------------------------------------------------------------------------");

        for (Student student : students) {
            String majorName = (student.getMajor() != null) ? student.getMajor().getName() : "N/A";
            String facultyPrefix = (student.getFaculty() != null) ? student.getFaculty().getPrefix() : "N/A";
            String formattedBirthDate = (student.getBirth() != null) ? student.getBirth().format(DATE_FORMATTER) : "N/A";

            System.out.printf(
                    "%-12s | %-22s | %-32s | %-10s | %-12s | %-10s | %-5.2f%n",
                    student.getId(),
                    student.getName(),
                    majorName,
                    facultyPrefix,
                    formattedBirthDate,
                    student.getStatus(),
                    student.getGpa()
            );
        }

        System.out.println("=========================================================================================================================");
        System.out.println();
    }

    public void displayOneStudent(Student student) {
        if (student == null) {
            System.out.println("\nStudent not found.");
            return;
        }
        String majorName = (student.getMajor() != null) ? student.getMajor().getName() : "N/A";
        String facultyPrefix = (student.getFaculty() != null) ? student.getFaculty().getPrefix() : "N/A";
        String formattedBirthDate = (student.getBirth() != null) ? student.getBirth().format(DATE_FORMATTER) : "N/A";
        System.out.printf(
                "%-12s | %-22s | %-32s | %-10s | %-12s | %-10s%n",
                student.getId(),
                student.getName(),
                majorName,
                facultyPrefix,
                formattedBirthDate,
                student.getStatus()
        );
    }
}
