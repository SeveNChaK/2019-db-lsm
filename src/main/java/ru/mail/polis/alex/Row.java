package ru.mail.polis.alex;

import java.nio.ByteBuffer;

import org.jetbrains.annotations.NotNull;

import ru.mail.polis.Record;

import static ru.mail.polis.alex.Constants.*;

final class Row implements Comparable<Row> {
    private final int index;
    private final ByteBuffer key;
    private final ByteBuffer value;
    private final int status;

    private Row(final int index,
            @NotNull final ByteBuffer key,
            @NotNull final ByteBuffer value,
            final int status) {
        this.index = index;
        this.key = key;
        this.value = value;
        this.status = status;
    }

    public static Row of(final int index,
            @NotNull final ByteBuffer key,
            @NotNull final ByteBuffer value,
            final int status) {
        return new Row(index, key, value, status);
    }

    /**
     * Creates an object of class Record.
     *
     * @return Record
     */
    Record getRecord() {
        if (isDead()) {
            return Record.of(key, TOMBSTONE);
        } else {
            return Record.of(key, value);
        }
    }

    boolean isDead() {
        return status == DEAD;
    }

    ByteBuffer getKey() {
        return key.asReadOnlyBuffer();
    }

    ByteBuffer getValue() {
        return value.asReadOnlyBuffer();
    }

    private int getIndex() {
        return index;
    }

    @Override
    public int compareTo(@NotNull final Row o) {
        if (key.compareTo(o.getKey()) == 0) {
            return -Integer.compare(index, o.getIndex());
        }
        return key.compareTo(o.getKey());
    }
}
