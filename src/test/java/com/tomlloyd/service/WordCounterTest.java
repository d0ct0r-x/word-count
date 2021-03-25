package com.tomlloyd.service;

import com.tomlloyd.util.ResourceUtils;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

class WordCounterTest {

    private static final Path TEST_FILES_DIR = ResourceUtils.getResourcePath("e2e-test-files");
    private static final String INPUT_FILENAME = "input.txt";
    private static final String OUTPUT_FILENAME = "output.txt";
    private WordCounterFactory wordCounterFactory = new DefaultWordCounterFactory();

    @TestFactory
    Stream<DynamicTest> process_forValidFile_expectCorrectOutput() throws IOException {
        WordCounter wordCounter = wordCounterFactory.getInstance();
        return listTests().stream()
                .map(test -> dynamicTest(test, () -> runTest(test, wordCounter)));
    }

    private void runTest(String test, WordCounter wordCounter) throws IOException {

        // GIVEN
        String inputFilepath = resolveInputFilepath(test);
        String ouputFilepath = resolveOutputFilepath(test);
        String expectedContent = readFileAsString(ouputFilepath);

        // WHEN
        String actualContent = wordCounter.process(inputFilepath);

        // THEN
        assertThat(actualContent, is(expectedContent));
    }

    private List<String> listTests() throws IOException {
        try (Stream<Path> stream = Files.list(TEST_FILES_DIR)) {
            return stream
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.toList());
        }
    }

    private String resolveInputFilepath(String testFolder) {
        return TEST_FILES_DIR.resolve(Path.of(testFolder, INPUT_FILENAME)).toString();
    }

    private String resolveOutputFilepath(String testFolder) {
        return TEST_FILES_DIR.resolve(Path.of(testFolder, OUTPUT_FILENAME)).toString();
    }

    private String readFileAsString(String filepath) throws IOException {
        return Files.readString(Path.of(filepath));
    }
}