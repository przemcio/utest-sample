package pl.testng.ch10;


import java.util.ArrayList;
import java.util.List;

public class Stack<T> {

    private List<T> list = new ArrayList<T>();

    public int size() {
        return list.size();
    }

    public Stack<T> push(T inputElement) {

        list.add(0, inputElement);

        return this;
    }

    public T pop() {

        if (list.size() == 0) {
            return null;
        } else {

            T element = list.get(0);
            list.remove(0);

            return element;
        }
    }
}
