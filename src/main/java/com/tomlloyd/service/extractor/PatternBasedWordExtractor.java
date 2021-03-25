package com.tomlloyd.service.extractor;

import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PatternBasedWordExtractor implements WordExtractor {

    private static final Pattern WORD_PATTERN = getWordPattern();

    @Override
    public List<String> extractWords(String line) {
        return WORD_PATTERN.matcher(line).results()
                .map(MatchResult::group)
                .collect(Collectors.toList());
    }

    private static Pattern getWordPattern() {
        return Pattern.compile(String.join("|",
                LetterPattern.resolve(), NumberPattern.resolve(), "&"));
    }

    private enum LetterPattern {
        PREFIX("\\w*"),
        BODY("[A-Za-z](['_-][A-Za-z])*"),
        SUFFIX("\\w*");

        private String pattern;

        LetterPattern(String pattern) {
            this.pattern = pattern;
        }

        String getPattern() {
            return pattern;
        }

        static String resolve() {
            return Stream.of(LetterPattern.values())
                    .map(LetterPattern::getPattern)
                    .collect(Collectors.joining());
        }
    }

    private enum NumberPattern {
        PREFIX("[£$+-]?"),
        BODY("\\d+([.,/:]\\d+)*"),
        SUFFIX("%?");

        private String pattern;

        NumberPattern(String pattern) {
            this.pattern = pattern;
        }

        String getPattern() {
            return pattern;
        }

        static String resolve() {
            return Stream.of(NumberPattern.values())
                    .map(NumberPattern::getPattern)
                    .collect(Collectors.joining());
        }
    }
}
