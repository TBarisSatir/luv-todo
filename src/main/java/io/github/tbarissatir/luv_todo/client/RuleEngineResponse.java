package io.github.tbarissatir.luv_todo.client;

public class RuleEngineResponse {

    private boolean matched;
    private String ruleName;
    private String ruleMsg;

    public boolean isMatched() {
        return matched;
    }

    public String getRuleName() {
        return ruleName;
    }

    public String getRuleMsg() {
        return ruleMsg;
    }
}
