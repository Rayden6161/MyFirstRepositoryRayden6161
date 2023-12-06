package com.example;

public class Operation {
    private double x;
    private double y;
    private double result;
    private String operation;

    public Operation(double x, double y, String operation) {
        this.x = x;
        this.y = y;
        this.operation = operation;
    }

    public void calculate() {
        this.result = switch(operation) {
            case "+" -> x+y;
            case "-" -> x-y;
            case "*" -> x*y;
            case "/" -> x/y;
            default -> 0;
        };
    }
}
