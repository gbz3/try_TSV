package com.github.gbz3;

import net.jqwik.api.Example;
import org.assertj.core.api.Assertions;

public class DataElementTest {

    @Example
    void dataのバイト長はDE項目で定義した長さの範囲内() {
        var tooShortString = " ".repeat(0);
        var tooLongString = " ".repeat(1000);

        for (var de : DataElementNumber.values()) {
            Assertions.assertThatThrownBy(() -> DataElement.of(de, tooShortString))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("data is too short");

            Assertions.assertThatThrownBy(() -> DataElement.of(de, tooLongString))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("data is too long");
        }
    }

}
