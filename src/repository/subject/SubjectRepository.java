package repository.subject;

import model.subject.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class SubjectRepository {

    private final Map<String, Subject> subjectsMap;

    public SubjectRepository(Map<String, Subject> subjectsMap) {
        this.subjectsMap = subjectsMap;
    }


    public void load() {
        String filePath = "src/data/subjects.txt";

        File file = new File(filePath);
        if (!file.exists()) {
            try {
                if (file.getParentFile() != null) {
                    file.getParentFile().mkdirs();
                }
                file.createNewFile();
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    writer.write("SUBJECT|ID|NAME|CREDITS|TYPE");
                    writer.newLine();
                }
            } catch (IOException e) {
                System.out.println("Error creating subjects file: " + e.getMessage());
            }
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] parts = line.split("\\|");
                if (parts.length == 5 && parts[1].equalsIgnoreCase("ID")) {
                    continue;
                }
                if (parts[0].equalsIgnoreCase("SUBJECT") && parts.length == 5) {
                    String id = parts[1].trim().toUpperCase();
                    if (!id.matches("SUB[1-9][0-9]*")) {
                        continue;
                    }
                    String name = parts[2];
                    int credits = Integer.parseInt(parts[3]);
                    String type = parts[4];
                    Subject subject = createSubject(id, name, credits, type);
                    if (subject != null) {
                        subjectsMap.put(id, subject);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading subjects: " + e.getMessage());
        }
    }

    public void save() {
        String filePath = "src/data/subjects.txt";

        try {
            File file = new File(filePath);
            if (file.getParentFile() != null) {
                file.getParentFile().mkdirs();
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write("SUBJECT|ID|NAME|CREDITS|TYPE");
                writer.newLine();
                for (Subject subject : subjectsMap.values()) {
                    String type = getSubjectType(subject);
                    writer.write("SUBJECT|" + subject.getId() + "|" + subject.getName()
                            + "|" + subject.getCredits() + "|" + type);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving subjects: " + e.getMessage());
        }
    }

    public ArrayList<Subject> getAll() {
        return new ArrayList<>(subjectsMap.values());
    }

    public Subject findById(String id) {
        return id == null ? null : subjectsMap.get(id.toUpperCase());
    }

    public void add(Subject subject) {
        subjectsMap.put(subject.getId(), subject);
    }

    public void deleteById(String id) {
        if (id != null) {
            subjectsMap.remove(id.toUpperCase());
        }
    }

    private Subject createSubject(String id, String name, int credits, String type) {
        return Subject.create(type, id, name, credits);
    }

    private String getSubjectType(Subject subject) {
        return subject.getType();
    }
}
