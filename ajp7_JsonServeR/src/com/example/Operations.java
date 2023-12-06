package com.example;

public class Operations {
private double x;
private double y;
private double result;
private String operations;

public Operations(double  x,double y ,String operations){
    this.x=x;
    this.y= y;
    this.operations = operations;

}
    public void calculate() {
        this.result = switch(operations) {
            case "+" -> x+y;
            case "-" -> x-y;
            case "*" -> x*y;
            case "/" -> x/y;
            default -> 0;

        };
}
}
