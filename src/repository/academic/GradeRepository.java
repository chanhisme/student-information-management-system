package repository.academic;

import model.student.Student;
import model.subject.Subject;
import java.io.*;
import java.util.ArrayList;
import java.util.Map;

public class GradeRepository {
    private final String FILE_PATH = "src/data/grade.txt";


    public void save(ArrayList<Student> students) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write("StudentID|SubjectID|Score");
            writer.newLine();

            for (Student student : students) {
                for (Map.Entry<String, Double> entry : student.getSubjectGrades().entrySet()) {
                    writer.write(student.getId() + "|" + entry.getKey() + "|" + entry.getValue());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to save grades.", e);
        }
    }

    public void load(ArrayList<Student> students) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line = reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");

                String studentId = parts[0];
                String subjectId = parts[1];
                double score = Double.parseDouble(parts[2]);

                for (Student student : students) {
                    if (student.getId().equals(studentId)) {
                        student.getSubjectGrades().put(subjectId, score);
                        break;
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load grades.", e);
        }
    }

    public void add(Student student, Subject subject, double score) {
        student.getSubjectGrades().put(subject.getId(), score);
    }

    public void delete (Student student, Subject subject) {
        student.getSubjectGrades().remove(subject.getId());
    }


}
