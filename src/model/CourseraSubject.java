package model;

public class CourseraSubject extends Subject {
    public CourseraSubject(String id, String name, int credits) {
        super(id, name, credits);
    }

    @Override
    public double calculateFinalScore(double score) {
        double finalScore = Double.parseDouble(toString(score + 1.0));
        return Math.min(10, finalScore);
    }
}
