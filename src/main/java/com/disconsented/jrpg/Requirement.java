package com.disconsented.jrpg;

public class Requirement {
    private String type;
    private String operator;
    private String object;

    Requirement(String type, String operator, String object) {
        this.type = type;
        this.operator = operator;
        this.object = object;
    }

    public String getType() {
        return type;
    }

    public String getOperator() {
        return operator;
    }

    public String getObject() {
        return object;
    }
}
