package DataStructure;

public class MyLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size = 0;

    private static class Node<T> {
        T value;
        Node<T> next;

        Node(T value) {
            this.value = value;
        }
    }

    public void addLast(T newValue) {
        Node<T> newNode = new Node<>(newValue);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = tail.next;
        }
        ++size;
    }

    public boolean remove(T element) {
        if (head == null) {
            return false;
        }

        if (head.value.equals(element)) {
            head = head.next;
            size--;

            if (head == null) {
                tail = null;
            }

            return true;
        }

        Node<T> current = head;

        while (current.next != null) {
            if (current.next.value.equals(element)) {
                if (current.next == tail) {
                    tail = current;
                }

                current.next = current.next.next;
                size--;

                return true;
            }

            current = current.next;
        }

        return false;
    }

    public boolean contains(T element) {
        Node<T> current = head;

        while (current != null) {
            if (current.value.equals(element)) {
                return true;
            }

            current = current.next;
        }

        return false;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

}
