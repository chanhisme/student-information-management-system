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
            try{
                System.out.print("Enter score: ");
                double score = Double.parseDouble(scanner.nextLine());
                if(score >= 0){
                    return Double.parseDouble(String.format("%.2f", score));
                }
            } catch (NumberFormatException e) {
                ConsoleColor.printError(e.getMessage());
            }


        }

    }
}
