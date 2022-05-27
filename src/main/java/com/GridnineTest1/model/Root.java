package com.GridnineTest1.model;

import org.springframework.stereotype.Component;

@Component
public class Root {
    private Result result;

    public Result getResult() { return result; }
    public void setResult(Result result) { this.result = result; }

    @Override
    public String toString() {
        return "Root{" +
                "result=" + result +
                '}';
    }
}
