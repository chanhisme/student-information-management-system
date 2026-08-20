package repository;

import model.Faculty;
import model.Major;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FacultyRepository {

    private ArrayList<Faculty> faculties;

    public FacultyRepository() {
        faculties = new ArrayList<>();
    }

    public void load() {

        String filePath = "src/data/major.txt";
        Faculty currentFaculty = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line;

            while ((line = reader.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split("\\|");

                if (parts[0].equals("FACULTY")) {

                    String prefix = parts[1];
                    String name = parts[2];

                    currentFaculty = new Faculty(prefix, name);
                    faculties.add(currentFaculty);

                } else if (parts[0].equals("MAJOR")) {

                    String name = parts[1];

                    Major major = new Major(name);
                    currentFaculty.addMajor(major);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to load faculty data.", e);
        }
    }

    public ArrayList<Faculty> getAll() {
        return faculties;
    }
}