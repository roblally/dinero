package cc.rjl.dinero;

import java.util.Set;
import org.junit.*;
import static cc.rjl.dinero.Collections.*;
import static cc.rjl.dinero.OpUtils.*;
import static cc.rjl.dinero.Dinero.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class DineroTest {
    private Iterable<String> listOfStrings;
    private Iterable<Integer> listOfIntegers;

    @Before public void setUp() throws Exception {
        listOfStrings = iterable("A", "B", "C");
        listOfIntegers = iterable(1, 2, 3);
    }

    @Test public void emptyChainReturnsUnmodifiedCollection() {
        assertThat($(listOfStrings), is(listOfStrings));
    }

    @Test public void outputIsNotTheSameObjectAsInput() {
        assertFalse($(listOfStrings) == listOfStrings);
    }

    @Test public void selectFiltersTarget() {
        assertThat(
                $(listOfStrings,
                        select(isNotB())),
                is(iterable("A", "C")));
    }

    @Test public void multipleSelectsFilterTarget() {
        assertThat(
                $(listOfStrings,
                        select(isNotB()),
                        select(isNotC())),
                is(iterable("A")));
    }

    @Test public void rejectFiltersTarget() {
        assertThat(
                $(listOfStrings,
                        reject(isB())),
                is(iterable("A", "C")));
    }

    @Test public void combiningSelectAndReject() {
        assertThat(
                $(listOfStrings,
                        reject(isB()),
                        select(isNotC())),
                is(iterable("A")));
    }

    @Test public void itemsFilteredByEarlierOperationsAreNotPassedToLaterOperations() {
        assertThat(
                $(listOfStrings,
                        select(alwaysFalse()),
                        select(alwaysThrow())),
                is(Collections.<String>empty()));
    }

    @Test public void operationsAreNotLimitedToStrings() {
        assertThat(
                $(listOfIntegers,
                        select(alwaysTrue())),
                is(listOfIntegers));
    }

    @Test public void collectTransformsInputToOutput() {
        assertThat(
                $(listOfIntegers,
                        collect(doubleValue())),
                is(iterable(2, 4, 6)));
    }

    @Test public void collectCanTransformType() {
        assertThat(
                $(listOfIntegers,
                        collect(convertToString())),
                is(iterable("1", "2", "3")));
    }

    @Test public void multipleCollectsCanBeIncluded() {
        assertThat(
                $(listOfIntegers,
                        collect(doubleValue()),
                        collect(convertToString())),
                is(iterable("2", "4", "6")));
    }

    @Test public void filterOperationsAndTransformOperationsCanBeInterleaved() {
        assertThat(
                $(listOfIntegers,
                        collect(doubleValue()),
                        reject(is2()),
                        collect(convertToString()),
                        reject(is4())),
                is(iterable("6")));
    }

    @Test public void filterOperationsAndTransformOperationsInterleavedWithAnonymousClassesToSeeHowItLooks() {
        $(listOfIntegers,
                collect(new F1<Integer, Integer>() {
                    public Integer apply(Integer integer) {
                        return integer * 2;
                    }
                }),
                reject(new Predicate<Integer>() {
                    public boolean apply(Integer integer) {
                        return integer.equals(2);
                    }
                }),
                collect(new F1<Integer, String>() {
                    public String apply(Integer integer) {
                        return "" + integer;
                    }
                }),
                reject(new Predicate<String>() {
                    public boolean apply(String string) {
                        return string.equals("4");
                    }
                }));
    }

    @Test public void setOperationReturnsSet() {
        Set<Integer> out =
                $(iterable(1, 1, 2, 2, 3, 3),
                        Dinero.<Integer>asSet());

        assertThat(out, is(set(1, 2, 3)));
    }

    @Test public void checkAllAritiesUpToTwentyArePresent() {
        assertThat(
                $(iterable(0),
                        collect(add1())),
                is(iterable(1)));

        assertThat(
                $(iterable(0),
                        collect(add1()),
                        collect(add1())),
                is(iterable(2)));

        assertThat(
                $(iterable(0),
                        collect(add1()),
                        collect(add1()),
                        collect(add1())),
                is(iterable(3)));

        assertThat(
                $(iterable(0),
                        collect(add1()),
                        collect(add1()),
                        collect(add1()),
                        collect(add1())),
                is(iterable(4)));

        assertThat(
                $(iterable(0),
                        collect(add1()),
                        collect(add1()),
                        collect(add1()),
                        collect(add1()),
                        collect(add1())),
                is(iterable(5)));

        assertThat(
                $(iterable(0),
                        collect(add1()),
                        collect(add1()),
                        collect(add1()),
                        collect(add1()),
                        collect(add1()),
                        collect(add1())),
                is(iterable(6)));

        assertThat(
                $(iterable(0),
                        collect(add1()),
                        collect(add1()),
                        collect(add1()),
                        collect(add1()),
                        collect(add1()),
                        collect(add1()),
                        collect(add1())),
                is(iterable(7)));

        //TODO... keep typing
    }
}
