package service.student;

import model.student.Student;
import model.subject.Subject;

import java.util.Stack;

public class CourseRegistrationService {
    public enum ActionType {REGISTER, DROP}

    public static class RegistrationAction {
        private final Student student;
        private final Subject subject;
        private final ActionType type;

        public RegistrationAction(Student student, Subject subject, ActionType type) {
            this.student = student;
            this.subject = subject;
            this.type = type;
        }

        public Student getStudent() {
            return student;
        }

        public Subject getSubject() {
            return subject;
        }

        public ActionType getType() {
            return type;
        }
    }

    private final Stack<RegistrationAction> undoStack = new Stack<>();
    private final Stack<RegistrationAction> redoStack = new Stack<>();

    public void registerCourse(Student student, Subject subject) {
        if (student.getRegisteredSubjects().contains(subject)) {
            throw new IllegalArgumentException("Student has already registered for this subject.");
        }
        student.getRegisteredSubjects().add(subject);
        undoStack.push(new RegistrationAction(student, subject, ActionType.REGISTER));
        redoStack.clear();
    }

    public void dropCourse(Student student, Subject subject) {
        if (!student.getRegisteredSubjects().contains(subject)) {
            throw new IllegalArgumentException("Student is not registered for this subject.");
        }
        student.getRegisteredSubjects().remove(subject);
        undoStack.push(new RegistrationAction(student, subject, ActionType.DROP));
        redoStack.clear();
    }

    public boolean undo() {
        if (undoStack.isEmpty()) {
            return false;
        }
        RegistrationAction action = undoStack.pop();
        Student student = action.getStudent();
        Subject subject = action.getSubject();

        if (action.getType() == ActionType.REGISTER) {
            student.getRegisteredSubjects().remove(subject);
        } else {
            student.getRegisteredSubjects().add(subject);
        }
        redoStack.push(action);
        return true;
    }

    public boolean redo() {
        if (redoStack.isEmpty()) {
            return false;
        }
        RegistrationAction action = redoStack.pop();
        Student student = action.getStudent();
        Subject subject = action.getSubject();

        if (action.getType() == ActionType.REGISTER) {
            student.getRegisteredSubjects().add(subject);
        } else {
            student.getRegisteredSubjects().remove(subject);
        }
        undoStack.push(action);
        return true;
    }
}
