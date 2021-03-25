package com.tomlloyd.service.formatter;

import com.tomlloyd.model.WordStats;

import java.util.StringJoiner;
import java.util.stream.Collectors;

public class DefaultWordStatsFormatter implements WordStatsFormatter {

    @Override
    public String format(WordStats stats) {
        final int wordCount = stats.getWordCount();

        final StringJoiner joiner = new StringJoiner(System.lineSeparator());
        joiner.add(String.format("Word count = %,d", wordCount));
        joiner.add(String.format("Average word length = %.3f", stats.getAverageWordLength()));

        stats.getWordLengthToFrequencyMap().entrySet().stream()
                .map(entry -> String.format("Number of words of length %,d is %,d", entry.getKey(), entry.getValue()))
                .forEach(joiner::add);

        if (wordCount > 0) {
            joiner.add(String.format("The most frequently occurring word length is %,d, for word lengths of %s",
                    stats.getMaxNumberOfWordsOfSameLength(), getMostFrequentWordLengthsAsString(stats)));
        }

        return joiner.toString();
    }

    private String getMostFrequentWordLengthsAsString(WordStats stats) {
        return stats.getMostFrequentWordLengths().stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" & "));
    }
}
