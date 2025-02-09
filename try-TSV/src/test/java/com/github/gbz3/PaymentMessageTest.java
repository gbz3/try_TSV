package com.github.gbz3;

import net.jqwik.api.*;
import net.jqwik.api.constraints.*;
import org.assertj.core.api.Assertions;

public class PaymentMessageTest {

    @Example
    void 例外がスローされることを確認する() {
        Assertions.assertThatThrownBy(() -> PaymentMessage.of(-1, 999))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid mti");

        Assertions.assertThatThrownBy(() -> PaymentMessage.of(10000, 999))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid mti");

        Assertions.assertThatThrownBy(() -> PaymentMessage.of(9999, -1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid transaction code");

        Assertions.assertThatThrownBy(() -> PaymentMessage.of(9999, 1000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid transaction code");
    }

    @Property
    void MTIは4桁の数字かつTransactionCodeは３桁の数字(
            @ForAll @IntRange(max = 9999) int mti,
            @ForAll @IntRange(max = 999) int transactionCode
    ) {
        var message = PaymentMessage.of(mti, transactionCode);
        Assertions.assertThat(message.getMTI()).containsOnlyDigits();
        Assertions.assertThat(message.getMTI().length()).isEqualTo(4);
        Assertions.assertThat(message.getTransactionCode()).containsOnlyDigits();
        Assertions.assertThat(message.getTransactionCode().length()).isEqualTo(3);
    }

}
