package service.student;

import model.student.Student;
import model.subject.Subject;

public class AcademicService {

    public void addGrade(Student student, Subject subject, double score) {
        if (!student.getRegisteredSubjects().contains(subject)) {
            throw new IllegalArgumentException("Student must register for the subject before receiving a grade.");
        }
        student.getSubjectGrades().put(subject.getId(), score);
        calculateGpa(student);
    }


    public void updateGrade(Student student, Subject subject, double score) {
        if (!student.getRegisteredSubjects().contains(subject)) {
            throw new IllegalArgumentException("Student must register for the subject before receiving a grade.");
        }
        if (!student.getSubjectGrades().containsKey(subject.getId())) {
            throw new IllegalArgumentException("No grade found to update. Add grade first.");
        }
        student.getSubjectGrades().put(subject.getId(), score);
        calculateGpa(student);
    }

    public void deleteGrade(Student student, Subject subject) {
        if (!student.getRegisteredSubjects().contains(subject)) {
            throw new IllegalArgumentException("Student must register for the subject before receiving a grade.");
        }
        student.getSubjectGrades().remove(subject.getId());
        calculateGpa(student);
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
}
