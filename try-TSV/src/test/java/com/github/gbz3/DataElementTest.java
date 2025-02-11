package com.github.gbz3;

import net.jqwik.api.*;
import org.assertj.core.api.Assertions;

import java.util.Set;

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

    @Provide
    Arbitrary<DataElement> anyDE() {
        return Arbitraries.of(
                DataElement.of(DataElementNumber.DE_001, "        "),
                DataElement.of(DataElementNumber.DE_002, "123"),
                DataElement.of(DataElementNumber.DE_024, "123456")
        );
    }

    @Property
    void 番号が同じDE項目は同一と判定(
            @ForAll("anyDE") DataElement de1,
            @ForAll("anyDE") DataElement de2
    ) {
        var set = Set.of(de1);
        Assertions.assertThat(set.contains(de2)).isEqualTo(de1.getNumber() == de2.getNumber());
        Assertions.assertThat(set.contains(de2)).isEqualTo(de1.equals(de2));
    }

}
