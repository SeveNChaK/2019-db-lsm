package ru.mail.polis.alex;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.jetbrains.annotations.NotNull;

final class Compaction {
    private static final String TMP = ".tmp";
    private static final ByteBuffer MIN_KEY = ByteBuffer.allocate(0);
    private static final int START_FILE_INDEX = 0;

    private Compaction() {
    }

    static void compactFile(@NotNull final File rootDir,
            @NotNull final Collection<FileTable> fileTables) throws IOException {
        List<Iterator<Row>> tableIterators = new LinkedList<>();
        for (FileTable ft : fileTables) {
            tableIterators.add(ft.iterator(MIN_KEY));
        }
        Iterator<Row> rowIterator = AlexDAO.getActualRowIterator(tableIterators);
        String fileName = Constants.PREFIX + START_FILE_INDEX + TMP;
        File resultFile = new File(rootDir, fileName);
        FileTable.write(resultFile, rowIterator);
        for (FileTable ft : fileTables) {
            ft.close();
            ft.delete();
        }
        fileTables.clear();
        String dbName = Constants.PREFIX + START_FILE_INDEX + Constants.SUFFIX;
        File resultFileDb = new File(rootDir, dbName);
        Files.move(resultFile.toPath(), resultFileDb.toPath(), StandardCopyOption.ATOMIC_MOVE);
        fileTables.add(new FileTable(resultFileDb));
    }
}
