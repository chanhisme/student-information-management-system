package service.student;

import model.student.Student;
import repository.student.StudentRepository;

import java.util.Map;

public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void addStudent(Student student) {
        if (studentRepository.findById(student.getId()) != null) {
            throw new IllegalArgumentException("Student ID already exists.");
        }
        studentRepository.add(student);
        studentRepository.save();
    }

    public Student findById(String id) {
        return studentRepository.findById(id);
    }

    public Map<String, Student> getAllStudents() {
        return studentRepository.getAll();
    }

    public java.util.List<Student> getStudentsSorted(java.util.Comparator<Student> comparator) {
        java.util.List<Student> sortedList = new java.util.ArrayList<>(studentRepository.getAll().values());
        if (!sortedList.isEmpty()) {
            mergeSort(sortedList, 0, sortedList.size() - 1, comparator);
        }
        return sortedList;
    }

    private void mergeSort(java.util.List<Student> list, int left, int right, java.util.Comparator<Student> comparator) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(list, left, mid, comparator);
            mergeSort(list, mid + 1, right, comparator);
            merge(list, left, mid, right, comparator);
        }
    }

    private void merge(java.util.List<Student> list, int left, int mid, int right, java.util.Comparator<Student> comparator) {
        java.util.List<Student> temp = new java.util.ArrayList<>();
        int i = left;
        int j = mid + 1;

        while (i <= mid && j <= right) {
            if (comparator.compare(list.get(i), list.get(j)) <= 0) {
                temp.add(list.get(i));
                i++;
            } else {
                temp.add(list.get(j));
                j++;
            }
        }

        while (i <= mid) {
            temp.add(list.get(i));
            i++;
        }

        while (j <= right) {
            temp.add(list.get(j));
            j++;
        }

        for (int k = 0; k < temp.size(); k++) {
            list.set(left + k, temp.get(k));
        }
    }

    public void updateStudent(Student student) {
        if (studentRepository.findById(student.getId()) == null) {
            throw new IllegalArgumentException("Student not found.");
        }
        studentRepository.save();
    }

    public void deleteStudent(String id) {
        if (studentRepository.findById(id) == null) {
            throw new IllegalArgumentException("Student not found.");
        }
        studentRepository.deleteById(id);
        studentRepository.save();
    }
}
