package repository;

import model.Faculty;
import model.Major;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class FacultyRepository {

    private final Map<String, Faculty> facultiesMap;

    public FacultyRepository() {
        facultiesMap = new LinkedHashMap<>();
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
                    facultiesMap.put(prefix, currentFaculty);

                } else if (parts[0].equals("MAJOR") && currentFaculty != null) {

                    String id = parts[1];
                    String name = parts[2];

                    Major major = new Major(id, name);
                    currentFaculty.addMajor(major);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to load faculty data.", e);
        }
    }

    public ArrayList<Faculty> getAll() {
        return new ArrayList<>(facultiesMap.values());
    }

    public Faculty getFacultyByPrefix(String prefix) {
        return facultiesMap.get(prefix);
    }
}