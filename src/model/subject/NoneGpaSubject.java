package model.subject;

public class NoneGpaSubject extends Subject {
    public NoneGpaSubject(String id, String name, int credits) {
        super(id, name, credits, false);
    }

    @Override
    public double calculateFinalScore(double score) {
        return 0;
    }

    @Override
    public String getType() {
        return "NONE_GPA";
    }
}
