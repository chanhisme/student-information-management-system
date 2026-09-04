package model.subject;

import java.util.Arrays;
import java.util.List;

public abstract class Subject {
    private String id;
    private String name;
    private int credits;
    private boolean calculatedGpa;

    public Subject(String id, String name, int credits) {
        this(id, name, credits, true);
    }

    public Subject(String id, String name, int credits, boolean calculatedGpa) {
        this.id = id;
        this.name = name;
        this.credits = credits;
        this.calculatedGpa = calculatedGpa;
    }

    public static Subject create(String type, String id, String name, int credits) {
        switch (type.toUpperCase()) {
            case "NORMAL":
                return new NormalSubject(id, name, credits);
            case "ELECTIVE":
                return new ElectiveSubject(id, name, credits);
            case "COURSERA":
                return new CourseraSubject(id, name, credits);
            case "NONE_GPA":
                return new NoneGpaSubject(id, name, credits);
            default:
                throw new IllegalArgumentException("Unknown subject type: " + type);
        }
    }

    public static List<String> getRegisteredTypes() {
        return Arrays.asList("NORMAL", "ELECTIVE", "COURSERA", "NONE_GPA");
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

    public boolean isCalculatedGpa() {
        return calculatedGpa;
    }

    public void setCalculatedGpa(boolean calculatedGpa) {
        this.calculatedGpa = calculatedGpa;
    }

    protected double round(double score) {
        return Math.round(score * 100) / 100.0;
    }

    public abstract double calculateFinalScore(double score);

    public abstract String getType();
}
