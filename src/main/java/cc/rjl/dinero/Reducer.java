package cc.rjl.dinero;

public interface Reducer<I, O> {
    void handle(I target);

    O result();
}
