package org.example;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.*;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args)
    {
        Boolean continueCalculate = true;
        while(continueCalculate){
            String actionChoice = getActionChoice();
            if(!Objects.equals(actionChoice, "q")) {
                performAction(actionChoice);
            } else {
                continueCalculate = false;
            }
        }
    }

    public static String getActionChoice(){
        presentActionBanner();
        String actionChoice;
        do {
            actionChoice = scanner.next();
        } while (isChoiceQualityGood(actionChoice) == false);
        return actionChoice;
    }

    public static void presentActionBanner() {
        String[][] choices = {{"+","Add"},{"-","Subtract"},{"*","Multiply"},{"/","Divide"},{"q","Quit"}};
        for(String[] choice : choices){
            System.out.print("\u001B[41m \u001B[30m" + choice[0] + "\u001B[37m \u001B[0m");
            System.out.print(" " + choice[1] + "  ");
        }
        System.out.println("");
    }

    public static Boolean isChoiceQualityGood(String actionChoice) {
        return Pattern.matches("[\\Q+-*/\\EqQ]", actionChoice);
    }
    public static void performAction(String actionChoice){
        double numberOne = getDouble();
        double numberTwo = getDouble();
        double result = 0;
        Boolean divisionByZero = false;
        switch(actionChoice) {
            case "+":

                result = addition(numberOne, numberTwo);
                break;
            case "-":
                result = subtraction(numberOne, numberTwo);
                break;
            case "*":
                result = multiplication(numberOne, numberTwo);
                break;
            case "/":
                if(numberTwo != 0){
                    result = division(numberOne, numberTwo);
                } else {
                    divisionByZero = true;
                }
                break;
        }
        if(!divisionByZero){
            System.out.println(String.format("The calculated result is %f", result));
        } else {
            System.out.println("Division by zero will not give a meaningful result");
        }
    }

    public static double addition(double numberOne, double numberTwo){
        return numberOne + numberTwo;
    }

    public static double addition(double[] numbers){
        double result = 0;
        for (double number : numbers) {
            result += number;
        }
        return result;
    }

    public static double subtraction(double numberOne, double numberTwo){
        return numberOne - numberTwo;
    }

    public static double subtraction(double[] numbers){
        double result = numbers[0];
        for(int i = 1; i < numbers.length; i++){
            result -= numbers[i];
        }
        return result;
    }

    public static double division(double numberOne, double numberTwo){
        return numberOne / numberTwo;
    }

    public static double multiplication(double numberOne, double numberTwo){
        return numberOne * numberTwo;
    }

    public static Double getDouble() {
        System.out.println("Enter a number: ");
        String entry = "";
        Boolean invalidNumber = true;
        do {
            entry = scanner.next();
            if(Pattern.matches("[0-9\\Q.,\\E]*",  entry)) {
                invalidNumber = false;
            } else {
                System.out.println("Number not valid. Please enter a valid number:");
            }
        } while (invalidNumber);
        Double number = Double.parseDouble(entry);
        return number;
    }

    public static Double[] enterMoreNumbers(Double numberOne, Double numberTwo) {
        System.out.println("Enter more numbers? (y/n)");
        String response = scanner.next();
        ArrayList<Double> moreNumbers = new ArrayList<Double>();
        if(Objects.equals(response, "y")){
            moreNumbers.add(numberOne);
            moreNumbers.add(numberTwo);
            Boolean continueEntering = true;
            do {
                System.out.println("Enter a new number (stop by hitting enter on an empty line): ");

            } while (continueEntering);
        }
        //Double[] moreNumbers = new Double[2];
        return moreNumbers;

    }
}