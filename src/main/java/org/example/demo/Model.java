package org.example.demo;

import java.util.Collection;

public interface Model<T> extends Collection<T> {

    /**
     * Adds an element to the data structure
     * @param element
     */
    void addElement(T element);


    /**
     * Returns an element from the data structure.
     * Depending on the data structure, this can either be a random element
     *  or a specific one (like the top element of a stack)
     * @return
     */
    T getElement();


    /**
     * Removes an element from the data structure.
     * Depending on the data structure, this can either be a random element
     *  or a specific one (like the top element of a stack)
     */
    void removeElement();


    /**
     * Removes all elements from data structure
     */
    void clear();


    /**
     * Returns true if there are no elements in the list
     * @return
     */
    boolean isEmpty();

    /**
     * Returns the number of elements in the list
     * @return
     */
    int size();
}
