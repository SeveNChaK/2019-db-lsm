package ru.mail.polis.alex;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Collection;

import org.jetbrains.annotations.NotNull;

public class Compaction {
    private static final String TMP = ".tmp";

    private Compaction() {
    }

    public static void compactFile(@NotNull final File rootDir,
            @NotNull final Collection<FileTable> fileTables) throws IOException {

    }
}
