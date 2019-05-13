package ru.mail.polis.alex;

import java.nio.ByteBuffer;

import org.jetbrains.annotations.NotNull;

final class Bytes {
    private Bytes() {
    }

    static ByteBuffer fromInt(final int i) {
        return ByteBuffer.allocate(Integer.BYTES).putInt(i).rewind();
    }
}
