package com.tomlloyd.service;

import com.tomlloyd.model.WordStats;
import com.tomlloyd.service.extractor.WordExtractor;
import com.tomlloyd.service.formatter.WordStatsFormatter;
import com.tomlloyd.service.reader.LineReader;

import java.io.IOException;

public class DefaultWordCounter implements WordCounter {

    private LineReader reader;
    private WordExtractor extractor;
    private WordStatsFormatter formatter;

    public DefaultWordCounter(LineReader reader,
                              WordExtractor extractor,
                              WordStatsFormatter formatter) {

        this.reader = reader;
        this.extractor = extractor;
        this.formatter = formatter;
    }

    @Override
    public String process(String filepath) throws IOException {
        final WordStats stats = new WordStats();
        reader.read(filepath, line -> processLine(line, stats));

        return formatter.format(stats);
    }

    private void processLine(String line, WordStats stats) {
        stats.addWords(extractor.extractWords(line));
    }
}
