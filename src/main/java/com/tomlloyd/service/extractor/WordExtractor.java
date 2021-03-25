package com.tomlloyd.service.extractor;

import java.util.List;

public interface WordExtractor {

    List<String> extractWords(String line);
}
