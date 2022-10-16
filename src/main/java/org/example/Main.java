package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.*;

public class Main {

    final private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args)
    {
        boolean continueCalculate = true;
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
            actionChoice = scanner.nextLine();
        } while (!isChoiceQualityGood(actionChoice));
        return actionChoice;
    }

    public static void presentActionBanner() {
        String[][] choices = {{"+","Add"},{"-","Subtract"},{"*","Multiply"},{"/","Divide"},{"q","Quit"}};
        for(String[] choice : choices){
            System.out.print("\u001B[41m \u001B[30m" + choice[0] + "\u001B[37m \u001B[0m");
            System.out.print(" " + choice[1] + "  ");
        }
        System.out.println();
    }

    public static Boolean isChoiceQualityGood(String actionChoice) {
        return Pattern.matches("[\\Q+-*/\\EqQ]", actionChoice);
    }
    public static void performAction(String actionChoice){
        Double[] numbers = getCorrectNumbers();
        double result = 0;
        boolean divisionByZero = false;
        switch(actionChoice) {
            case "+":
                if(numbers.length > 2){
                    result = addition(numbers);
                } else {
                    result = addition(numbers[0], numbers[1]);
                }
                break;
            case "-":
                if(numbers.length > 2){
                    result = subtraction(numbers);
                } else {
                    result = subtraction(numbers[0], numbers[1]);
                }
                break;
            case "*":
                result = multiplication(numbers[0], numbers[1]);
                break;
            case "/":
                if(numbers[1] != 0){
                    result = division(numbers[0], numbers[1]);
                } else {
                    divisionByZero = true;
                }
                break;
        }
        if(!divisionByZero){
            System.out.printf("The calculated result is %f%n", result);
        } else {
            System.out.println("Division by zero will not give a meaningful result");
        }
    }

    public static double addition(double numberOne, double numberTwo){
        return numberOne + numberTwo;
    }

    public static double addition(Double[] numbers){
        double result = 0;
        for (double number : numbers) {
            result += number;
        }
        return result;
    }

    public static double subtraction(double numberOne, double numberTwo){
        return numberOne - numberTwo;
    }

    public static double subtraction(Double[] numbers){
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

    public static Double[] getCorrectNumbers() {
        boolean incorrectNumbers;
        List<Double> numbers = new ArrayList<>();
        do {
            incorrectNumbers = false;
            numbers.clear();
            System.out.println("Enter the two numbers separated by a space (further numbers for adding and subtracting possible): ");
            String userEntry = scanner.nextLine();
            String[] userEntries = userEntry.split(" ", 0);
            double number;
            for (String entry : userEntries) {
                try {
                    number = Double.parseDouble(entry);
                    numbers.add(number);
                } catch (NumberFormatException e) {
                    System.out.println("The numbers were not properly entered. Please try again");
                    incorrectNumbers = true;
                    break;
                }
            }
            if(numbers.size() < 2) {
                incorrectNumbers = true;
            }
        } while (incorrectNumbers);
        Double[] correctNumbers = new Double[numbers.size()];
        correctNumbers = numbers.toArray(correctNumbers);
        return correctNumbers;
    }
}