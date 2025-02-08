package com.github.gbz3;

import net.jqwik.api.*;
import net.jqwik.api.constraints.*;
import org.assertj.core.api.Assertions;

public class PropertyBasedTests {

    @Property
    boolean absoluteValueOfAllNumbersIsPositive(
            @ForAll @IntRange(min = Integer.MIN_VALUE + 1) int anInteger) {
        return Math.abs(anInteger) >= 0;
    }

    @Property
    void lengthOfConcatenatedStringIsGreaterThanLengthOfEach(
            @ForAll @WithNull(value = 0) @NotEmpty String str1,
            @ForAll @WithNull(value = 0) @NotEmpty String str2) {
        String str = str1 + str2;
        Assertions.assertThat(str.length()).isGreaterThan(str1.length());
        Assertions.assertThat(str.length()).isGreaterThan(str2.length());
    }

}
