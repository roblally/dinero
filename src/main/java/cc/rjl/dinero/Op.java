package cc.rjl.dinero;

import java.util.*;

public abstract class Op<In, Out> {
    abstract boolean execute(OpBinding x);

    Collection createReturnType() {
        return new ArrayList();
    }

    abstract static class Filter<T> extends Op<T, T> {
        abstract boolean include(T target);

        boolean execute(OpBinding binding) {
            if(include((T)binding.getTarget()))
                return true;

            binding.setShouldReturnTarget(false);
            return false;
        }
    }

    abstract static class Transform<T1, T2> extends Op<T1, T2> {
        abstract T2 transform(T1 target);

        boolean execute(OpBinding binding) {
            binding.setTarget(transform((T1)binding.getTarget()));
            return true;
        }
    }
}
