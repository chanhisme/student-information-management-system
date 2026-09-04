package repository.academic;

import model.student.Student;
import model.subject.Subject;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;

public class RegistrationRepository {

    private final String FILE_PATH = "src/data/registered.txt";
    private final Map<String, Student> students;
    private final Map<String , Subject> subjectMap;
    public RegistrationRepository(Map<String, Student> students, Map<String, Subject> subjectMap) {
        this.students = students;
        this.subjectMap = subjectMap;
    }

    public void save() {
        try {
            File file = new File(FILE_PATH);
            if (file.getParentFile() != null) {
                file.getParentFile().mkdirs();
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write("StudentID|SubjectID|");
                writer.newLine();
                for (Student student : students.values()) {
                    for (Subject subject : student.getRegisteredSubjects()) {
                        writer.write(student.getId() + "|" + subject.getId());
                        writer.newLine();
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void load() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            try {
                if (file.getParentFile() != null) {
                    file.getParentFile().mkdirs();
                }
                file.createNewFile();
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    writer.write("StudentID|SubjectID|");
                    writer.newLine();
                }
            } catch (IOException e) {
                throw new RuntimeException("Failed to create registration file.", e);
            }
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            reader.readLine();

            String line;
            while( (line = reader.readLine()) != null){
                String[] parts = line.split("\\|");
                if(parts.length != 2){
                    continue;
                }
                String studentId = parts[0].trim().toUpperCase();
                String subjectId = parts[1].trim().toUpperCase();
                Student student = students.get(studentId);
                Subject subject = subjectMap.get(subjectId);
                if (student != null && subject != null) {
                    student.getRegisteredSubjects().addLast(subject);
                }

            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load registrations.", e);
        }
    }

    public void add(Student student, Subject subject) {
        student.getRegisteredSubjects().addLast(subject);
    }


    public void delete(Student student, Subject subject) {
        student.getRegisteredSubjects().remove(subject);
    }
}
