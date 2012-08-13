package cc.rjl.dinero;

public interface Predicate<T> {
    boolean apply(T arg1);

    public static class Util {
        public static <T> Predicate<T> equality(final T value) {
            return new Predicate<T>() {
                public boolean apply(T arg1) {
                    return arg1.equals(value);
                }
            };
        }

        public static <T> Predicate<T> not(final Predicate<T> predicate) {
            return new Predicate<T>() {
                public boolean apply(T arg1) {
                    return !predicate.apply(arg1);
                }
            };
        }
    }
}
