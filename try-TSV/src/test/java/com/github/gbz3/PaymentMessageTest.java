package com.github.gbz3;

import net.jqwik.api.*;
import net.jqwik.api.constraints.*;
import org.assertj.core.api.Assertions;

public class PaymentMessageTest {

    @Property
    void indicatorOfMessageIs4Number(
            @ForAll @IntRange(min = 0, max = 9999) int mti
    ) {
        var message = PaymentMessage.of(mti);
        Assertions.assertThat(message.getMTI()).containsOnlyDigits();
        Assertions.assertThat(message.getMTI().length()).isEqualTo(4);
    }
}
