package com.tomlloyd.model;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class WordStats {

    private Map<Integer, Integer> wordLengthToFrequencyMap = new TreeMap<>();

    public void addWords(List<String> words) {
        words.forEach(this::addWord);
    }

    public void addWord(String word) {
        wordLengthToFrequencyMap.merge(word.length(), 1, Integer::sum);
    }

    public int getWordCount() {
        return wordLengthToFrequencyMap.values().stream()
                .reduce(0, Integer::sum);
    }

    public double getAverageWordLength() {
        final double totalWordLength = wordLengthToFrequencyMap.entrySet().stream()
                .map(entry -> (double) entry.getKey() * entry.getValue())
                .reduce(0d, Double::sum);

        return totalWordLength == 0
                ? 0
                : totalWordLength / getWordCount();
    }

    public int getMaxNumberOfWordsOfSameLength() {
        return wordLengthToFrequencyMap.values().stream()
                .reduce(0, Integer::max);
    }

    public List<Integer> getMostFrequentWordLengths() {
        return wordLengthToFrequencyMap.entrySet().stream()
                .filter(entry -> entry.getValue() == getMaxNumberOfWordsOfSameLength())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public Map<Integer, Integer> getWordLengthToFrequencyMap() {
        return wordLengthToFrequencyMap;
    }
}
