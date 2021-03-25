package com.tomlloyd.service;

import com.tomlloyd.service.extractor.PatternBasedWordExtractor;
import com.tomlloyd.service.extractor.WordExtractor;
import com.tomlloyd.service.formatter.DefaultWordStatsFormatter;
import com.tomlloyd.service.formatter.WordStatsFormatter;
import com.tomlloyd.service.reader.BufferedLineReader;
import com.tomlloyd.service.reader.LineReader;

public class DefaultWordCounterFactory implements WordCounterFactory {

    @Override
    public WordCounter getInstance() {
        final LineReader reader = new BufferedLineReader();
        final WordExtractor extractor = new PatternBasedWordExtractor();
        final WordStatsFormatter formatter = new DefaultWordStatsFormatter();

        return new DefaultWordCounter(reader, extractor, formatter);
    }
}
