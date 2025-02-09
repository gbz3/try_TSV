package com.github.gbz3;

import org.jetbrains.annotations.NotNull;

public class PaymentMessage {
    private final int _mti;
    private final int _transactionCode;

    private PaymentMessage(int mti, int transactionCode) {
        this._mti = mti;
        this._transactionCode = transactionCode;
    }

    public static @NotNull PaymentMessage of(int mti, int transactionCode) {
        if (mti < 0 || 9999 < mti) throw new IllegalArgumentException("Invalid mti");
        if (transactionCode < 0 || 999 < transactionCode) throw new IllegalArgumentException("Invalid transaction code");
        return new PaymentMessage(mti, transactionCode);
    }

    public String getMTI() {
        return String.format("%04d", _mti);
    }

    public String getTransactionCode() {
        return String.format("%03d", _transactionCode);
    }

}
