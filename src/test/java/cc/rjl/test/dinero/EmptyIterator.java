package cc.rjl.test.dinero;

import java.util.Iterator;

class EmptyIterator<T> implements Iterator<T> {
    public boolean hasNext() {
        return false;
    }

    public T next() {
        throw new UnsupportedOperationException();
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}
