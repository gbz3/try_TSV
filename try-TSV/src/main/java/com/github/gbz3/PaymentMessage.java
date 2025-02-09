package com.github.gbz3;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class PaymentMessage {
    private final int _mti;

    private PaymentMessage(int mti) {
        this._mti = mti;
    }

    @Contract("_ -> new")
    public static @NotNull PaymentMessage of(int mti) {
        if (mti < 0 || 9999 < mti) throw new IllegalArgumentException();
        return new PaymentMessage(mti);
    }

    public String getMTI() {
        return String.format("%04d", _mti);
    }

}
