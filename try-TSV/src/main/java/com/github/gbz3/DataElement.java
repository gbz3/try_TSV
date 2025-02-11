package com.github.gbz3;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.nio.charset.StandardCharsets;

public class DataElement {
    private final DataElementNumber _number;
    private final String _data;

    private DataElement(DataElementNumber number, String data) {
        _number = number;
        _data = data;
    }

    @Contract("_, _ -> new")
    public static @NotNull DataElement of(@NotNull DataElementNumber de, @NotNull String data) {
        var bytes = data.getBytes(StandardCharsets.UTF_8);
        de.checkDataLength(bytes);
        return new DataElement(de, data);
    }

    public @NotNull DataElementNumber getNumber() {
        return _number;
    }

    public @NotNull String getData() {
        return _data;
    }

    /**
     * DE項目の番号が同じなら同一と判定する
     */
    @Override
    public int hashCode() {
        return _number.hashCode();
    }

    /**
     * DE項目の番号が同じなら同一と判定する
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof DataElement
                && _number.hashCode() == ((DataElement) obj)._number.hashCode();
    }

}
