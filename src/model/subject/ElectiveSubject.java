package model.subject;

public class ElectiveSubject extends Subject {
    public ElectiveSubject(String id, String name, int credits) {
        super(id, name, credits);
    }

    @Override
    public double calculateFinalScore(double score) {
        return round(score);
    }

    @Override
    public String getType() {
        return "ELECTIVE";
    }
}
