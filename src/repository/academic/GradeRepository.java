package repository.academic;

import model.student.Student;
import model.subject.Subject;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;

public class GradeRepository {
    private final String FILE_PATH = "src/data/grade.txt";
    private final Map<String, Student> students;

    public GradeRepository(Map<String, Student> students) {
        this.students = students;
    }


    public void save() {
        try {
            File file = new File(FILE_PATH);
            if (file.getParentFile() != null) {
                file.getParentFile().mkdirs();
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write("StudentID|SubjectID|Score");
                writer.newLine();

                for (Student student : students.values()) {
                    for (Map.Entry<String, Double> entry : student.getSubjectGrades().entrySet()) {
                        writer.write(student.getId() + "|" + entry.getKey() + "|" + entry.getValue());
                        writer.newLine();
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to save grades.", e);
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
                    writer.write("StudentID|SubjectID|Score");
                    writer.newLine();
                }
            } catch (IOException e) {
                throw new RuntimeException("Failed to create grade file.", e);
            }
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");

                if (parts.length != 3) {
                    continue;
                }

                String studentId = parts[0];
                String subjectId = parts[1];
                double score = Double.parseDouble(parts[2]);

                Student student = students.get(studentId);
                if (student != null) {
                    student.getSubjectGrades().put(subjectId, score);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load grades.", e);
        }
    }

    public void add(Student student, Subject subject, double score) {
        student.getSubjectGrades().put(subject.getId(), score);
    }

    public void delete(Student student, Subject subject) {
        student.getSubjectGrades().remove(subject.getId());
    }


}
