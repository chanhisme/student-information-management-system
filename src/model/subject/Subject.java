package model.subject;

public abstract class Subject {
    protected String id;
    protected String name;
    protected int credits;
    protected boolean CalculatedGpa;

    public Subject(String id, String name, int credits) {
        this.id = id;
        this.name = name;
        this.credits = credits;
        this.CalculatedGpa = true;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public boolean CalculatedGpa() {
        return CalculatedGpa;
    }

    public void setCalculatedGpa(boolean CalculatedGpa) {
        this.CalculatedGpa = CalculatedGpa;
    }
    public abstract double calculateFinalScore(double score);

    public String toString(double score) {
        return String.format("%.2f", score);
    }

}
