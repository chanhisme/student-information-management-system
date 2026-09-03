package service.academic;

import DataStructure.MyStack;
import model.student.Student;
import model.subject.Subject;
import repository.academic.GradeRepository;
import repository.academic.RegistrationRepository;


public class RegistrationService {


    public enum ActionType {REGISTER, DROP}
    private final RegistrationRepository registrationRepository;

    public RegistrationService(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

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

    private final MyStack<RegistrationAction> undoStack = new MyStack<>();
    private final MyStack<RegistrationAction> redoStack = new MyStack<>();

    public void registerCourse(Student student, Subject subject) {
        if (student.getRegisteredSubjects().contains(subject)) {
            throw new IllegalArgumentException("Student has already registered for this subject.");
        }
        student.getRegisteredSubjects().addLast(subject);
        undoStack.push(new RegistrationAction(student, subject, ActionType.REGISTER));
        registrationRepository.add(student, subject);
        redoStack.clear();

        registrationRepository.save();
    }

    public void dropCourse(Student student, Subject subject) {
        if (!student.getRegisteredSubjects().contains(subject)) {
            throw new IllegalArgumentException("Student is not registered for this subject.");
        }
        student.getRegisteredSubjects().remove(subject);
        undoStack.push(new RegistrationAction(student, subject, ActionType.DROP));
        registrationRepository.delete(student, subject);
        redoStack.clear();
        registrationRepository.save();
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
            student.getRegisteredSubjects().addLast(subject);
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
            student.getRegisteredSubjects().addLast(subject);
        } else {
            student.getRegisteredSubjects().remove(subject);
        }
        undoStack.push(action);
        return true;
    }
}
