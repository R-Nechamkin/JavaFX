package org.example.dataStructureAnimation;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;



public class StackModel<T> implements Model<T>{
    public Deque<T> stack;

    public StackModel(){
        stack = new ArrayDeque<>();
    }

    @Override
    public void addElement(T element) {
        stack.push(element);
    }

    @Override
    public T getElement() {
        return stack.peek();
    }

    @Override
    public void removeElement() {
        stack.pop();
    }

    @Override
    public void clear() {
        stack.clear();
    }

    @Override
    public int size() {
        return stack.size();
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return stack.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return stack.iterator();
    }

    @Override
    public Object[] toArray() {
        return stack.toArray(new Object[0]);
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return stack.toArray(a);
    }

    @Override
    public boolean add(T t) {
        return stack.add(t);
    }

    @Override
    public boolean remove(Object o) {
        return stack.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return stack.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return stack.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return stack.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return stack.retainAll(c);
    }
}
