package service.academic;

import model.student.Student;
import model.subject.Subject;
import repository.academic.GradeRepository;
import java.util.List;


public class GradeService {
    private final GradeRepository gradeRepository;
    public GradeService(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }
    public void addGrade(Student student, Subject subject, double score) {
        if (!student.getRegisteredSubjects().contains(subject)) {
            throw new IllegalArgumentException("Student must register for the subject before receiving a grade.");
        }
        gradeRepository.add(student, subject, score);
        calculateGpa(student);
        gradeRepository.save();
    }


    public void updateGrade(Student student, Subject subject, double score) {
        if (!student.getRegisteredSubjects().contains(subject)) {
            throw new IllegalArgumentException("Student must register for the subject before receiving a grade.");
        }
        if (!student.getSubjectGrades().containsKey(subject.getId())) {
            throw new IllegalArgumentException("No grade found to update. Add grade first.");
        }
        gradeRepository.add(student, subject, score);
        calculateGpa(student);
        gradeRepository.save();

    }

    public void deleteGrade(Student student, Subject subject) {
        if (!student.getRegisteredSubjects().contains(subject)) {
            throw new IllegalArgumentException("Student must register for the subject before receiving a grade.");
        }
        gradeRepository.delete(student, subject);
        calculateGpa(student);
        gradeRepository.save();
    }

    public void calculateGpa(Student student) {
        double totalWeightedScore = 0;
        int totalCredits = 0;
        for (Subject subject : student.getRegisteredSubjects()) {
            Double score = student.getSubjectGrades().get(subject.getId());
            if (score != null && subject.isCalculatedGpa()) {
                double finalScore = subject.calculateFinalScore(score);
                totalWeightedScore += finalScore * subject.getCredits();
                totalCredits += subject.getCredits();
            }
        }

        if (totalCredits > 0) {
            student.setGpa(totalWeightedScore / totalCredits);
        } else {
            student.setGpa(0.0);
        }
    }
    public double roundGpa(double gpa) {
        return Double.parseDouble(String.format("%.2f", gpa));
    }

    public double[] calculateAverageGpa(List<Student> students) {
        double sum = 0;
        double maxGpa = Double.NEGATIVE_INFINITY;
        double minGpa = Double.POSITIVE_INFINITY;
        int count = 0;

        for (Student student : students) {
            double gpa = student.getGpa();

            if (gpa != 0.0) {
                count++;
                sum += gpa;
                maxGpa = Math.max(maxGpa, gpa);
                minGpa = Math.min(minGpa, gpa);
            }
        }

        if (count == 0) {
            return new double[]{0.0, 0.0, 0.0};
        }

        return new double[]{roundGpa(sum / count), roundGpa(maxGpa), roundGpa(minGpa)};
    }


}
