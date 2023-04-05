package org.example;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MyStack<T> {
    private T[] elements;


    public MyStack(Class<T> elements) {
        elements = (Class<T>) Array.newInstance(elements);
    }

    public void add(T value) {
        T[] temp = Arrays.copyOf(elements,elements.length+1);
        temp[elements.length-1] = value;
        elements = temp;
    }

    public void shrink(T value) {
        T[] temp = Arrays.copyOf(elements,elements.length-1);
        temp[elements.length-1] = value;
        elements = temp;
    }
}
