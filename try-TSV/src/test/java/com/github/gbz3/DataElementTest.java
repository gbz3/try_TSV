package com.github.gbz3;

import net.jqwik.api.*;
import net.jqwik.api.constraints.UseType;
import net.jqwik.api.statistics.Statistics;
import org.assertj.core.api.Assertions;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class DataElementTest {

    @Example
    void dataのバイト長がDE項目で定義した長さの範囲外なら例外() {
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

    private @NotNull String toCodepoints(String s) {
        var sb = new StringBuilder();
        sb.append("\"").append(s).append("\"");
        s.codePoints().forEach(c -> {
            sb.append(" U+").append(Integer.toHexString(c));
        });
        return sb.toString();
    }

    @Property
    void dataのバイト長はDE項目で定義した長さの範囲内(
            @ForAll @UseType @NotNull DataElement de
    ) {
        Statistics.label("DE").collect(de.getNumber().getNumber());
        Statistics.label("data.length").collect(de.getData().length());
        Statistics.label("number x data.length").collect(de.getNumber().getNumber(), de.getData().length());
        Statistics.label("DE-1.data").collect(de.getNumber() == DataElementNumber.DE_001? toCodepoints(de.getData()): null);

        Assertions.assertThat(de.getData().length()).isGreaterThan(0);
    }

    @Property
    void 番号が同じDE項目は同一と判定(
            @ForAll @UseType @NotNull DataElement de1,
            @ForAll @UseType @NotNull DataElement de2
    ) {
        Statistics.collect(de1.getNumber().getNumber(), de2.getNumber().getNumber());

        var set = Set.of(de1);
        Assertions.assertThat(set.contains(de2)).isEqualTo(de1.getNumber() == de2.getNumber());
        Assertions.assertThat(set.contains(de2)).isEqualTo(de1.equals(de2));
    }

}
