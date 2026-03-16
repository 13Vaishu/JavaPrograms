package experiment6;

import java.util.Scanner;

public class TableAndSquare {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Taking user input
        System.out.print("Enter a number: ");
        int num = sc.nextInt();

        // Display square
        int square = num * num;
        System.out.println("Square of " + num + " is: " + square);

        // Display multiplication table
        System.out.println("\nMultiplication Table of " + num + ":");
        for (int i = 1; i <= 10; i++) {
            System.out.println(num + " x " + i + " = " + (num * i));
        }

        sc.close();
    }
}