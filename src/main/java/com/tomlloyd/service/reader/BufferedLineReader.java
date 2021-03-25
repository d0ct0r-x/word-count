package com.tomlloyd.service.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Consumer;

public class BufferedLineReader implements LineReader {

    @Override
    public void read(String filepath, Consumer<String> lineConsumer) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filepath))) {
            reader.lines().forEach(lineConsumer);
        }
    }
}
