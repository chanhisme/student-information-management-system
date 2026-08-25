package view.faculty;

import java.util.ArrayList;
import java.util.Scanner;

import com.sun.security.sasl.ntlm.FactoryImpl;
import model.faculty.Faculty;
import view.BaseMenuView;

public class MenuFaultyManagementView extends BaseMenuView {

    public MenuFaultyManagementView(Scanner scanner) {
        super(scanner);
    }

    @Override
    public void showMenu() {
        System.out.println("========== FACULTY MANAGEMENT ==========");
        System.out.println("1. View all faculties");
        System.out.println("2. Add faculty");
        System.out.println("3. Update faculty");
        System.out.println("4. Delete faculty");
        System.out.println("5. Search faculty");
        System.out.println("6. Manage majors");
        System.out.println("7. View faculty details");
        System.out.println("0. Back");
    }

    public void displayAllFaculties(ArrayList <Faculty> faculties){
        if(faculties == null || faculties.isEmpty()){
            System.out.println("\nNo faculty found");
            return;
        }
        System.out.println("=========FACILITIES=========");
        for(int i = 0; i < faculties.size(); i++){
            Faculty faculty = faculties.get(i);
            System.out.printf("%d. %s - %s\n", (i+1), faculty.getPrefix(), faculty.getName());
        }
    }
}
