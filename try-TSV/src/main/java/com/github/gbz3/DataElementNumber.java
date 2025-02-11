package com.github.gbz3;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public enum DataElementNumber {
    DE_001(1, 0, 8, 8, "Bitmap, secondary"),
    DE_002(2, 0, 3, 3, "Transaction code"),
    DE_024(24, 2, 6, 19, "PAN"),
    ;

    private final int _number;
    private final int _numberOfDigitsInTheFieldLength;
    private final int _minOfDataLength;
    private final int _maxOfDataLength;
    private final String _name;

    DataElementNumber(int number, int digits, int min, int max, String name) {
        _number = number;
        _numberOfDigitsInTheFieldLength = digits;
        _minOfDataLength = min;
        _maxOfDataLength = max;
        _name = name;
    }

    public String getNumber() {
        return String.format("%03d", _number);
    }

    public int getNumOfDigits() {
        return _numberOfDigitsInTheFieldLength;
    }

    public void checkDataLength(byte[] data) {
        if (data == null) {
            throw new IllegalArgumentException("data is null");
        }
        if (data.length < _minOfDataLength) {
            throw new IllegalArgumentException(
                    String.format("data is too short: data.length=%d min=%d", data.length, _minOfDataLength)
            );
        }
        if (data.length > _maxOfDataLength) {
            throw new IllegalArgumentException(
                    String.format("data is too long: data.length=%d, max=%d", data.length, _maxOfDataLength)
            );
        }
    }

    @Contract(pure = true)
    public @NotNull String toString() {
        return String.format("%03d: %s", _number, _name);
    }
}
