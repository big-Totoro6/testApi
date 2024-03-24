package com.example.testapi.common;

public class Context {
    Strategy strategy;

    public Context() {
    }
    public Context(Strategy strategy) {
        this.strategy = strategy;
    }
    public String contextInterface(){
        return strategy.query();
    }

}
