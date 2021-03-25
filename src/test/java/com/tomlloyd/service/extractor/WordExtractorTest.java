package com.tomlloyd.service.extractor;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class WordExtractorTest {

    private WordExtractor extractor = new PatternBasedWordExtractor();

    @ParameterizedTest
    @MethodSource("lineAndWordsProvider")
    void extractWords_forValidLine_expectCorrectWords(String line, List<String> expectedWords) {

        // WHEN
        List<String> actualWords = extractor.extractWords(line);

        // THEN
        assertThat(actualWords, is(expectedWords));
    }

    static Stream<Arguments> lineAndWordsProvider() {
        return Stream.of(
                // letter patterns
                arguments("word", List.of("word")),
                arguments("two words", List.of("two", "words")),
                arguments("MiXed CaSe", List.of("MiXed", "CaSe")),
                arguments("lots  of  space", List.of("lots", "of", "space")),
                arguments("???", emptyList()),
                arguments("&", List.of("&")),
                arguments("apostrophe's", List.of("apostrophe's")),
                arguments("double-barrel", List.of("double-barrel")),
                arguments("'ignore' 'quotes'", List.of("ignore", "quotes")),
                arguments("ignore, comma'", List.of("ignore", "comma")),
                arguments("ignore. period'", List.of("ignore", "period")),

                // number patterns
                arguments("123", List.of("123")),
                arguments("1st", List.of("1st")),
                arguments("18/12/2021 10:02:52", List.of("18/12/2021", "10:02:52")),
                arguments("1,000", List.of("1,000")),
                arguments("2.5", List.of("2.5")),
                arguments("50%", List.of("50%")),
                arguments("£10.99", List.of("£10.99")),
                arguments("-3", List.of("-3"))
        );
    }
}