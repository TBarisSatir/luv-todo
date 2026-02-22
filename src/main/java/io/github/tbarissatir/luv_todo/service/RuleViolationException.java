package io.github.tbarissatir.luv_todo.service;

public class RuleViolationException extends RuntimeException {
    public RuleViolationException(String rule) {
        super(rule);
    }
}

