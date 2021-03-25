package com.tomlloyd;

import com.tomlloyd.service.DefaultWordCounterFactory;
import com.tomlloyd.service.WordCounter;
import com.tomlloyd.service.WordCounterFactory;

import java.io.IOException;

class WordCounterApplication {

    void run(String[] args) {
        final String filepath = resolveFilepath(args);

        try {
            final WordCounterFactory wordCounterFactory = new DefaultWordCounterFactory();
            final WordCounter wordCounter = wordCounterFactory.getInstance();
            System.out.println(wordCounter.process(filepath));
        }
        catch (IOException e) {
            System.err.println("ERROR: Failed to read file " + filepath);
            System.exit(1);
        }
    }

    private String resolveFilepath(String[] args) {
        if (args.length == 0) {
            System.out.println("No filepath argument provided");
            System.exit(0);
        }

        return args[0];
    }
}
