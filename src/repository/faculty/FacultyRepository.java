package repository.faculty;

import model.faculty.Faculty;
import model.faculty.Major;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class FacultyRepository {

    private final Map<String, Faculty> facultiesMap;

    public FacultyRepository(Map<String, Faculty> facultiesMap) {
        this.facultiesMap = facultiesMap;
    }


    public void save() {
        String filePath = "src/data/major.txt";
        try {
            File file = new File(filePath);
            if (file.getParentFile() != null) {
                file.getParentFile().mkdirs();
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write("TYPE|ID|NAME");
                writer.newLine();
                for (Faculty faculty : facultiesMap.values()) {
                    writer.write("FACULTY|" + faculty.getPrefix() + "|" + faculty.getName());
                    writer.newLine();
                    for (Major major : faculty.getMajors()) {
                        writer.write("MAJOR|" + major.getId() + "|" + major.getName());
                        writer.newLine();
                    }
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving faculties: " + e.getMessage());
        }
    }
    public void load() {

        String filePath = "src/data/major.txt";
        Faculty currentFaculty = null;

        File file = new File(filePath);
        if (!file.exists()) {
            try {
                if (file.getParentFile() != null) {
                    file.getParentFile().mkdirs();
                }
                file.createNewFile();
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    writer.write("TYPE|ID|NAME");
                    writer.newLine();
                }
            } catch (IOException e) {
                throw new RuntimeException("Failed to create faculty file.", e);
            }
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {

                if (line.trim().isEmpty() || line.startsWith("TYPE|")) {
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

    public void addFaculty(Faculty faculty){
        facultiesMap.put(faculty.getPrefix(), faculty);
    }

    public Faculty findByPreFix(String preFix){
        return facultiesMap.get(preFix);
    }
    public Faculty getFacultyByPrefix(String prefix) {
        return facultiesMap.get(prefix);
    }

    public void deleteFaculty(String preFix){
        facultiesMap.remove(preFix);
    }

    public void addMajor(Major major, String preFix){
        facultiesMap.get(preFix).addMajor(major);
    }
    public Major findMajorById(String id, String preFix){
        return facultiesMap.get(preFix).getMajorById(id);
    }

    public void deleteMajor(String id, String preFix){
        facultiesMap.get(preFix).removeMajor(id);
    }
}
