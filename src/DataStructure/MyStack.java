package DataStructure;

public class MyStack<T> {

    private Object[] elements;
    private int size;

    private static final int DEFAULT_CAPACITY = 10;
    private static final int GROWTH_FACTOR = 2;

    public MyStack() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    public void push(T element) {
        if (size == elements.length) {
            resize();
        }

        elements[size] = element;
        size++;
    }

    public T pop() {
        if (isEmpty()) {
            return null;
        }

        T element = (T)elements[size - 1];
        elements[size - 1] = null;
        size--;

        return element;
    }

    public T top() {
        if (isEmpty()) {
            return null;
        }

        return (T) elements[size - 1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void resize() {
        int newCapacity = elements.length * GROWTH_FACTOR;
        Object[] newElements = new Object[newCapacity];

        System.arraycopy(elements, 0, newElements, 0, size);

        elements = newElements;
    }
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }
}