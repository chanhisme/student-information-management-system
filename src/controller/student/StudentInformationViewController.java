package controller.student;

import DataStructure.MyLinkedList;
import model.student.Student;
import model.subject.Subject;
import service.student.AcademicService;
import service.student.StudentService;
import view.ConsoleColor;
import view.student.MenuStudentInformationView;

public class StudentInformationViewController {
    private final MenuStudentInformationView menuStudentInformationView;
    private final StudentService studentService;
    private final AcademicService academicService;

    public StudentInformationViewController(MenuStudentInformationView menuStudentInformationView,
                                            StudentService studentService,
                                            AcademicService academicService) {
        this.menuStudentInformationView = menuStudentInformationView;
        this.studentService = studentService;
        this.academicService = academicService;
    }

    public void run() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        while (true) {
            menuStudentInformationView.showMenu();
            int choice = menuStudentInformationView.inputChoice(0, 5);
            if (choice == 0) {
                return;
            }

            System.out.print("Enter Student ID: ");
            String studentId = scanner.nextLine().trim();
            Student student = studentService.findById(studentId);
            if (student == null) {
                ConsoleColor.printError("Student not found.");
                continue;
            }

            academicService.calculateGpa(student);

            switch (choice) {
                case 1:
                    System.out.println("\n--- Student Profile ---");
                    System.out.println("ID: " + student.getId());
                    System.out.println("Name: " + student.getName());
                    System.out.println("Faculty: " + (student.getFaculty() != null ? student.getFaculty().getName() : "N/A"));
                    System.out.println("Major: " + (student.getMajor() != null ? student.getMajor().getName() : "N/A"));
                    System.out.println("Birth Date: " + (student.getBirth() != null ? student.getBirth() : "N/A"));
                    System.out.println("Status: " + student.getStatus());
                    break;

                case 2:
                    System.out.println("\n--- Academic Transcript ---");
                    MyLinkedList<Subject> subjects = student.getRegisteredSubjects();
                    if (subjects.isEmpty()) {
                        System.out.println("No subjects registered.");
                    } else {
                        for (Subject s : subjects) {
                            Double grade = student.getSubjectGrades().get(s.getId());
                            String gradeStr = (grade != null) ? String.format("%.2f", s.calculateFinalScore(grade)) : "Not Graded";
                            System.out.println("- " + s.getName() + " (" + s.getCredits() + " credits): " + gradeStr);
                        }
                    }
                    break;

                case 3:
                    System.out.printf("\nGPA: %.2f%n", student.getGpa());
                    break;

                case 4:
                    int totalCredits = 0;
                    for (Subject s : student.getRegisteredSubjects()) {
                        totalCredits += s.getCredits();
                    }
                    System.out.println("\nTotal Registered Credits: " + totalCredits);
                    break;

                case 5:
                    int currentCredits = 0;
                    for (Subject s : student.getRegisteredSubjects()) {
                        currentCredits += s.getCredits();
                    }
                    int requiredCredits = 120;
                    double progress = ((double) currentCredits / requiredCredits) * 100;
                    System.out.println("\n--- Graduation Progress ---");
                    System.out.println("Completed Credits: " + currentCredits + " / " + requiredCredits);
                    System.out.printf("Progress: %.2f%%%n", progress);
                    break;
            }
        }
    }
}
