package model.subject;

public abstract class Subject {
    protected String id;
    protected String name;
    protected int credits;
    protected boolean CalculatedGpa;

    @FunctionalInterface
    public interface Creator {
        Subject create(String id, String name, int credits);
    }

    private static final java.util.Map<String, Creator> registry = new java.util.LinkedHashMap<>();

    static {
        register("NORMAL", NormalSubject::new);
        register("ELECTIVE", ElectiveSubject::new);
        register("COURSERA", CourseraSubject::new);
        register("NONE_GPA", NoneGpaSubject::new);
    }

    public static void register(String type, Creator creator) {
        registry.put(type.toUpperCase(), creator);
    }

    public static Subject create(String type, String id, String name, int credits) {
        Creator creator = registry.get(type.toUpperCase());
        if (creator == null) {
            throw new IllegalArgumentException("Unknown subject type: " + type);
        }
        return creator.create(id, name, credits);
    }

    public static java.util.Set<String> getRegisteredTypes() {
        return registry.keySet();
    }

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
    public abstract String getType();

    public String toString(double score) {
        return String.format("%.2f", score);
    }

}
