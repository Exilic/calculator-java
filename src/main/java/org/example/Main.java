package org.example;

import java.util.InputMismatchException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        Boolean continueCalculate = true;
        while(continueCalculate){
            String actionChoice = getActionChoice();
            if(actionChoice != "x") {
                performAction(actionChoice);
            } else {
                continueCalculate = false;
            }
        }
    }

    public static String getActionChoice(){
        presentActionBanner();
        Scanner scanner = new Scanner(System.in);
        String actionChoice = scanner.nextLine();
        if(isChoiceQualityBad(actionChoice)) {
            System.out.println("Unfortunate choice. Try again");
            getActionChoice();
        }
        return actionChoice;
    }

    public static void presentActionBanner() {
        String[][] choices = {{"+","Add"},{"-","Subtract"},{"*","Multiply"},{"/","Divide"},{"x","Exit"}};
        for(String[] choice : choices){
            System.out.print("\u001B[41m \u001B[30m" + choice[0] + "\u001B[37m \u001B[0m");
            System.out.print(" " + choice[1] + "  ");
        }
        System.out.println("");
    }

    public static Boolean isChoiceQualityBad(String actionChoice) {
        return false;
    }
    public static void performAction(String actionChoice){
        Scanner scanner = new Scanner(System.in);


        double numberOne = scanner.nextDouble();
        double numberTwo = scanner.nextDouble();
        String operatorChosen = scanner.next();
        double result = 0;
        Boolean divisionByZero = false;
        switch(operatorChosen) {
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

    public static String[] getOperatorMenuData() {
        String[] menuData = {"Indicate which operation to use: \n", "1. Addition\n", "2. Subtraction\n", "3. Multiplication\n", "4. Division\n"};
        return menuData;
    }

    public static String[] getNumbersMenuData() {
        String[] menuData = {"Enter the first number: ", "Enter the second number: "};
        return menuData;
    }

    public static Double getDouble(Scanner scanner) {
        try {
            double numberOne = scanner.nextDouble();
        } catch (InputMismatchException e) {
            System.out.println("Just use numbers and .\n");
            getDouble(scanner);
        }
        return 333.44;
    }
}