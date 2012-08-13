package cc.rjl.test.dinero;

import cc.rjl.dinero.*;
import static cc.rjl.dinero.Predicate.Util.*;

class OpUtils {
    static F1<Integer, Integer> add1() {
        return new F1<Integer, Integer>() {
            public Integer apply(Integer integer) {
                return integer + 1;
            }
        };
    }

    static F1<Integer, Integer> doubleValue() {
        return new F1<Integer, Integer>() {
            public Integer apply(Integer integer) {
                return integer * 2;
            }
        };
    }

    static F1<Integer, String> convertToString() {
        return new F1<Integer, String>() {
            public String apply(Integer integer) {
                return integer.toString();
            }
        };
    }

    public static Predicate<String> alwaysThrow() {
        return new Predicate<String>() {
            public boolean apply(String arg1) {
                throw new RuntimeException();
            }
        };
    }

    public static Predicate<String> alwaysFalse() {
        return new Predicate<String>() {
            public boolean apply(String arg1) {
                return false;
            }
        };
    }

    public static Predicate<Integer> alwaysTrue() {
        return new Predicate<Integer>() {
            public boolean apply(Integer arg1) {
                return true;
            }
        };
    }

    static Predicate<Integer> is2() {
        return equality(2);
    }

    static Predicate<String> is4() {
        return equality("4");
    }

    static Predicate<String> isB() {
        return equality("B");
    }

    static Predicate<String> isNotB() {
        return not(equality("B"));
    }

    static Predicate<String> isNotC() {
        return not(equality("C"));
    }
}
