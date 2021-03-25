package com.tomlloyd.service.reader;

import java.io.IOException;
import java.util.function.Consumer;

public interface LineReader {

    void read(String filepath, Consumer<String> lineConsumer) throws IOException;
}
