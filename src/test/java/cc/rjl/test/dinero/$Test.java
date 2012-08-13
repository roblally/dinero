package cc.rjl.test.dinero;

import cc.rjl.dinero.*;
import java.util.Set;
import org.junit.*;
import static cc.rjl.dinero.$.*;
import static cc.rjl.test.dinero.Collections.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class $Test {
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
                        select(OpUtils.isNotB())),
                is(iterable("A", "C")));
    }

    @Test public void multipleSelectsFilterTarget() {
        assertThat(
                $(listOfStrings,
                        select(OpUtils.isNotB()),
                        select(OpUtils.isNotC())),
                is(iterable("A")));
    }

    @Test public void rejectFiltersTarget() {
        assertThat(
                $(listOfStrings,
                        reject(OpUtils.isB())),
                is(iterable("A", "C")));
    }

    @Test public void combiningSelectAndReject() {
        assertThat(
                $(listOfStrings,
                        reject(OpUtils.isB()),
                        select(OpUtils.isNotC())),
                is(iterable("A")));
    }

    @Test public void itemsFilteredByEarlierOperationsAreNotPassedToLaterOperations() {
        assertThat(
                $(listOfStrings,
                        select(OpUtils.alwaysFalse()),
                        select(OpUtils.alwaysThrow())),
                is(Collections.<String>empty()));
    }

    @Test public void operationsAreNotLimitedToStrings() {
        assertThat(
                $(listOfIntegers,
                        select(OpUtils.alwaysTrue())),
                is(listOfIntegers));
    }

    @Test public void collectTransformsInputToOutput() {
        assertThat(
                $(listOfIntegers,
                        collect(OpUtils.doubleValue())),
                is(iterable(2, 4, 6)));
    }

    @Test public void collectCanTransformType() {
        assertThat(
                $(listOfIntegers,
                        collect(OpUtils.convertToString())),
                is(iterable("1", "2", "3")));
    }

    @Test public void multipleCollectsCanBeIncluded() {
        assertThat(
                $(listOfIntegers,
                        collect(OpUtils.doubleValue()),
                        collect(OpUtils.convertToString())),
                is(iterable("2", "4", "6")));
    }

    @Test public void filterOperationsAndTransformOperationsCanBeInterleaved() {
        assertThat(
                $(listOfIntegers,
                        collect(OpUtils.doubleValue()),
                        reject(OpUtils.is2()),
                        collect(OpUtils.convertToString()),
                        reject(OpUtils.is4())),
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
                        $.<Integer>asSet());

        assertThat(out, is(set(1, 2, 3)));
    }

    @Test public void checkAllAritiesUpToTwentyArePresent() {
        assertThat(
                $(iterable(0),
                        collect(OpUtils.add1())),
                is(iterable(1)));

        assertThat(
                $(iterable(0),
                        collect(OpUtils.add1()),
                        collect(OpUtils.add1())),
                is(iterable(2)));

        assertThat(
                $(iterable(0),
                        collect(OpUtils.add1()),
                        collect(OpUtils.add1()),
                        collect(OpUtils.add1())),
                is(iterable(3)));

        assertThat(
                $(iterable(0),
                        collect(OpUtils.add1()),
                        collect(OpUtils.add1()),
                        collect(OpUtils.add1()),
                        collect(OpUtils.add1())),
                is(iterable(4)));

        assertThat(
                $(iterable(0),
                        collect(OpUtils.add1()),
                        collect(OpUtils.add1()),
                        collect(OpUtils.add1()),
                        collect(OpUtils.add1()),
                        collect(OpUtils.add1())),
                is(iterable(5)));

        assertThat(
                $(iterable(0),
                        collect(OpUtils.add1()),
                        collect(OpUtils.add1()),
                        collect(OpUtils.add1()),
                        collect(OpUtils.add1()),
                        collect(OpUtils.add1()),
                        collect(OpUtils.add1())),
                is(iterable(6)));

        assertThat(
                $(iterable(0),
                        collect(OpUtils.add1()),
                        collect(OpUtils.add1()),
                        collect(OpUtils.add1()),
                        collect(OpUtils.add1()),
                        collect(OpUtils.add1()),
                        collect(OpUtils.add1()),
                        collect(OpUtils.add1())),
                is(iterable(7)));

        //TODO... keep typing
    }
}
