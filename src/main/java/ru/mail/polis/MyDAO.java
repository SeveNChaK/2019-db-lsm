package ru.mail.polis;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.SortedMap;
import java.util.TreeMap;

import org.jetbrains.annotations.NotNull;

public class MyDAO implements DAO {
    private final SortedMap<ByteBuffer, Record> db = new TreeMap<>();

    @Override
    public Iterator<Record> iterator(@NotNull final ByteBuffer from) throws IOException {
        return db.tailMap(from).values().iterator();
    }

    @Override
    public void upsert(@NotNull final ByteBuffer key, @NotNull final ByteBuffer value) throws IOException {
        db.put(key,Record.of(key,value));
    }

    @Override
    public void remove(@NotNull final ByteBuffer key) throws IOException {
        db.remove(key);
    }

    @Override
    public void close() throws IOException {
        //не надо пока
    }
}