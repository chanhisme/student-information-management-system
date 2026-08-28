package model.subject;

public class NormalSubject extends Subject{
    public NormalSubject(String id, String name, int credits) {
        super(id, name, credits);
    }

    @Override
    public double calculateFinalScore(double score) {
        return Double.parseDouble(toString(score));
    }

    @Override
    public String getType() {
        return "NORMAL";
    }

}
