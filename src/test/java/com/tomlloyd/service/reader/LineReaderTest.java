package com.tomlloyd.service.reader;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static com.tomlloyd.util.ResourceUtils.getResourcePathAsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LineReaderTest {

    private static final Consumer<String> NO_OP = x -> {};
    private LineReader reader = new BufferedLineReader();

    @Test
    void read_whenFileExists_consumeLines() throws Exception {

        // GIVEN
        String filepath = getResourcePathAsString("simple-file.txt");
        List<String> expected = List.of("A", "B", "C");
        List<String> actual = new ArrayList<>();

        // WHEN
        reader.read(filepath, actual::add);

        // THEN
        assertThat(actual, is(expected));
    }

    @Test
    void read_whenFileNotExists_throwException() {

        // GIVEN
        String filepath = "not-exists.txt";

        // THEN
        assertThrows(IOException.class, () -> reader.read(filepath, NO_OP));
    }
}