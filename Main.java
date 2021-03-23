package bullscows;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {


        Scanner input = new Scanner(System.in);
        // User input number which is length of the secret code
        System.out.println("Input the length of the secret code:");
        String inputCode = input.nextLine();
        boolean isLengthCorrect = false;

        try {

            int inputLengthOfCode = Integer.parseInt(inputCode);

            // if user enter number greater than 36, program stops execution and prints error
            if (inputLengthOfCode > 36) {
                System.out.println("Error: can't generate a secret number with a length of " + inputLengthOfCode + " because there aren't enough unique digits.");

            } else {

                // User must enter number of characters in secret code
                System.out.println("Input the number of possible symbols in the code:");
                int inputNumberOfSymbols = input.nextInt();

                if(inputNumberOfSymbols > inputLengthOfCode && inputLengthOfCode > 0 && inputNumberOfSymbols < 37) {
                    isLengthCorrect = true;
                } else if (inputNumberOfSymbols < inputLengthOfCode || inputLengthOfCode <= 0) {
                    System.out.println("Error: it's not possible to generate a code with a length of " + inputLengthOfCode + " with " + inputNumberOfSymbols + " unique symbols.");

                } else if(inputNumberOfSymbols >= 37) {
                    System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
                }

                if(isLengthCorrect) {
                    char[] num = randomCharacter(inputLengthOfCode, inputNumberOfSymbols);
                    grader(num);
                }
            }
        } catch (Exception ex) {
            System.out.println("Error: + " + inputCode + " isn't a valid number.");
        }

    }


    public static char[] randomCharacter(int inputLengthOfCode, int numberOfSymbols) {

        StringBuilder secretCodeLength = new StringBuilder();

        // Generating secret code message
        for (int i = 0; i < inputLengthOfCode; i++) {
            secretCodeLength.append("*");
        }

        char numberLength = Character.forDigit((numberOfSymbols - 1), numberOfSymbols);

        if (numberOfSymbols > 10) {
            System.out.println("The secret is prepared: " + secretCodeLength + " (0-9, a-" + numberLength + ").");
        } else {
            System.out.println("The secret is prepared: " + secretCodeLength + " (0-" + numberLength + ").");
        }

        System.out.println("Okay, let's start a game!");


        Random random = new Random();

        Integer[] arrayNum = new Integer[inputLengthOfCode];


        for (int i = 0; i < arrayNum.length; i++) {
            arrayNum[i] = random.nextInt(10);

        }

        for (int i = 0; i < arrayNum.length; i++) {
            for (int j = i + 1; j < arrayNum.length; j++) {
                if (arrayNum[i].equals(arrayNum[j])) {
                    arrayNum[i] = random.nextInt(10);
                    i = 0;
                    j = 0;
                }

            }
        }


        char[] arrayChar = new char[inputLengthOfCode];

        for (int i = 0; i < arrayChar.length; i++) {
            int randomCharNumber = random.nextInt(numberOfSymbols - 1);
            arrayChar[i] = Character.forDigit(randomCharNumber, numberOfSymbols);
        }


        for (int i = 0; i < arrayChar.length; i++) {
            for (int j = i + 1; j < arrayChar.length; j++) {
                if (arrayChar[i] == arrayChar[j]) {
                    int randomCharNumber = random.nextInt(numberOfSymbols - 1);
                    arrayChar[i] = Character.forDigit(randomCharNumber, numberOfSymbols);
                    i = 0;
                    j = 0;
                }
            }
        }


        System.out.println("Secret code : " + Arrays.toString(arrayChar));

        return arrayChar;

    }


    public static void grader(char[] secretCode) {

        int turn = 0;

        Scanner input = new Scanner(System.in);

        int bulls = 0;
        while (bulls != secretCode.length) {

            int cows = 0;
            bulls = 0;
            turn++;
            System.out.println("Turn " + turn + ":");
            String inputNum = input.nextLine();


            for (int i = 0; i < inputNum.length(); i++) {
                if (secretCode[i] == inputNum.charAt(i)) {
                    bulls += 1;

                }

            }

            for (int i = 0; i < inputNum.length(); i++) {
                for (int j = 0; j < secretCode.length; j++) {
                    if (i != j && inputNum.charAt(i) == secretCode[j]) {
                        cows += 1;
                    }
                }
            }

            if (bulls > 0 && cows > 0) {
                System.out.println("Grade: " + bulls + " bull(s) and " + cows + " cow(s).");
            } else if (bulls > 0 && cows == 0) {
                System.out.println("Grade: " + bulls + " bull(s).");
            } else if (cows > 0) {
                System.out.println("Grade: " + cows + " cow(s).");
            } else {
                System.out.println("Grade: None.");
            }

            if (bulls == secretCode.length) {
                System.out.println("Congratulations! You guessed the secret code.");
            }

        }
    }
}
