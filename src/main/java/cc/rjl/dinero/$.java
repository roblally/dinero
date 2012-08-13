package cc.rjl.dinero;

import java.util.*;

public class $ {
    public static <T> Collection<T> $(Iterable<T> input) {
        return execute(input, new CollectionReducer());
    }

    public static <T1, T2> T2 $(Iterable<T1> input, Reducer<T1, T2> reducer) {
        return execute(input, reducer);
    }

    public static <T1, T2> Collection<T2> $(Iterable<T1> input, Op<T1, T2> op1) {
        return execute(input, new CollectionReducer(), op1);
    }

    public static <T1, T2, T3> Collection<T3> $(Iterable<T1> input, Op<T1, T2> op1, Op<T2, T3> op2) {
        return execute(input, new CollectionReducer(), op1, op2);
    }

    public static <T1, T2, T3, T4> Collection<T4> $(Iterable<T1> input, Op<T1, T2> op1, Op<T2, T3> op2, Op<T3, T4> op3) {
        return execute(input, new CollectionReducer(), op1, op2, op3);
    }

    public static <T1, T2, T3, T4, T5> Collection<T5> $(Iterable<T1> input, Op<T1, T2> op1, Op<T2, T3> op2, Op<T3, T4> op3, Op<T4, T5> op4) {
        return execute(input, new CollectionReducer(), op1, op2, op3, op4);
    }

    public static <T1, T2, T3, T4, T5, T6> Collection<T6> $(Iterable<T1> input, Op<T1, T2> op1, Op<T2, T3> op2, Op<T3, T4> op3, Op<T4, T5> op4, Op<T5, T6> op5) {
        return execute(input, new CollectionReducer(), op1, op2, op3, op4, op5);
    }

    public static <T1, T2, T3, T4, T5, T6, T7> Collection<T7> $(Iterable<T1> input, Op<T1, T2> op1, Op<T2, T3> op2, Op<T3, T4> op3, Op<T4, T5> op4, Op<T5, T6> op5, Op<T6, T7> op6) {
        return execute(input, new CollectionReducer(), op1, op2, op3, op4, op5, op6);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8> Collection<T8> $(Iterable<T1> input, Op<T1, T2> op1, Op<T2, T3> op2, Op<T3, T4> op3, Op<T4, T5> op4, Op<T5, T6> op5, Op<T6, T7> op6, Op<T7, T8> op7) {
        return execute(input, new CollectionReducer(), op1, op2, op3, op4, op5, op6, op7);
    }

    private static <I, O> O execute(Iterable input, Reducer<I, O> reducer, Op... operations) {
        for(Object current : input) {
            Binding binding = new Binding(current);

            for(Op operation : operations)
                if(!operation.execute(binding)) break;

            if(binding.shouldReturnTarget()) reducer.handle((I)binding.getTarget());
        }

        return reducer.result();
    }

    public static <T> Op<T, T> select(final Predicate<T> predicate) {
        return new Op.Filter<T>() {
            public boolean include(T target) {
                return predicate.apply(target);
            }
        };
    }

    public static <T> Op<T, T> reject(final Predicate<T> predicate) {
        return new Op.Filter<T>() {
            public boolean include(T target) {
                return !predicate.apply(target);
            }
        };
    }

    public static <T1, T2> Op<T1, T2> collect(final F1<T1, T2> fn) {
        return new Op.Transform<T1, T2>() {
            public T2 transform(T1 target) {
                return fn.apply(target);
            }
        };
    }

    private static class CollectionReducer implements Reducer<Object, Collection> {
        private final Collection result = new ArrayList();

        public void handle(Object target) {
            result.add(target);
        }

        public Collection result() {
            return result;
        }
    }

    public static <T> Reducer<T, Set<T>> asSet() {
        return new Reducer<T, Set<T>>() {
            private final Set<T> result = new HashSet<>();

            public void handle(T target) {
                result.add(target);
            }

            public Set<T> result() {
                return result;
            }
        };
    }
}
