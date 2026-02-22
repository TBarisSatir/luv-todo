package io.github.tbarissatir.luv_todo.util;

public class StringUtils {

    public static String normalize(String input) {
        if (input == null) return null;
        return input.trim().replaceAll("\\s+", " ");
    }
}
