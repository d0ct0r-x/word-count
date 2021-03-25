package com.tomlloyd.service;

import java.io.IOException;

public interface WordCounter {

    String process(String filepath) throws IOException;
}
