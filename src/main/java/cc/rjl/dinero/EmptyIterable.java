package cc.rjl.dinero;

import java.util.Iterator;

class EmptyIterable<T> implements Iterable<T> {
    public Iterator<T> iterator() {
        return new EmptyIterator<>();
    }

    public boolean equals(Object obj) {
        if(!(obj instanceof Iterable)) return false;

        Iterable other = (Iterable)obj;

        return !other.iterator().hasNext();
    }

    public int hashCode() {
        return 1;
    }
}
