package cc.rjl.dinero;

public abstract class Op<I, O> {
    abstract boolean execute(Binding binding);

    abstract static class Filter<T> extends Op<T, T> {
        abstract boolean include(T target);

        boolean execute(Binding binding) {
            if(include((T)binding.getTarget()))
                return true;

            binding.setShouldReturnTarget(false);
            return false;
        }
    }

    abstract static class Transform<T1, T2> extends Op<T1, T2> {
        abstract T2 transform(T1 target);

        boolean execute(Binding binding) {
            binding.setTarget(transform((T1)binding.getTarget()));
            return true;
        }
    }
}
