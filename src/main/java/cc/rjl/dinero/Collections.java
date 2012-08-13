package cc.rjl.dinero;

import java.util.*;
import static java.util.Arrays.asList;

class Collections {
    static <T> Iterable<T> iterable(T... items) {
        return asList(items);
    }

    static <T> Iterable<T> empty() {
        return new EmptyIterable<>();
    }

    static <T> Set<T> set(T... items) {
        return new HashSet<>(asList(items));
    }
}
