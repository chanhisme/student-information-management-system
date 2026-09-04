package view.student;

import view.BaseMenuView;
import view.ConsoleColor;

import java.util.Scanner;

public class MenuGradeManageView extends BaseMenuView {
    public MenuGradeManageView(Scanner scanner) {
        super(scanner);
    }

    @Override
    public void showMenu() {
        System.out.println("\n========== GRADE MANAGEMENT ==========");
        System.out.println("1. Add Grade");
        System.out.println("2. Update Grade");
        System.out.println("3. Delete Grade");
        System.out.println("4. View Grades");
        System.out.println("0. Back");
        System.out.println("======================================");
    }

    public double inputGrade(){
        while(true){
            System.out.print("Enter score (Q to cancel): ");
            String line = readLineOrNull();
            if (line == null) {
                return -1;
            }
            try{
                double score = Double.parseDouble(line.trim());
                if(score >= 0 && score <= 10.0){
                    return Double.parseDouble(String.format("%.2f", score));
                }
                ConsoleColor.printError("Grade must be between 0.0 and 10.0.");
            } catch (NumberFormatException e) {
                ConsoleColor.printError("Please enter a valid number.");
            }
        }
    }

    public void displayCurrentGrade(double score) {
        System.out.println("Current Grade: " + score);
    }

}
