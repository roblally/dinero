package cc.rjl.dinero;

class Binding {
    private boolean shouldReturnTarget = true;
    private Object target;

    Binding(Object target) {
        this.target = target;
    }

    boolean shouldReturnTarget() {
        return shouldReturnTarget;
    }

    void setShouldReturnTarget(boolean value) {
        this.shouldReturnTarget = value;
    }

    Object getTarget() {
        return target;
    }

    void setTarget(Object target) {
        this.target = target;
    }
}
