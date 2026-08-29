package model.subject;

public class CourseraSubject extends Subject {
    public CourseraSubject(String id, String name, int credits) {
        super(id, name, credits);
    }

    @Override
    public double calculateFinalScore(double score) {
        return Math.min(10, round(score + 1.0));
    }

    @Override
    public String getType() {
        return "COURSERA";
    }
}
