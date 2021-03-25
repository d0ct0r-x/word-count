package com.tomlloyd.util;

import java.net.URL;
import java.nio.file.Path;
import java.util.Objects;

public final class ResourceUtils {

    public static String getResourcePathAsString(String filename) {
        return getResourcePath(filename).toString();
    }

    public static Path getResourcePath(String filename) {
        final URL url = Thread.currentThread().getContextClassLoader().getResource(filename);
        return Path.of(Objects.requireNonNull(url).getPath());
    }
}
