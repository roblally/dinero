package cc.rjl.dinero;

class OpBinding<Object> {
    private boolean shouldReturnTarget = true;
    private Object target;

    OpBinding(Object target) {
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
