package z.aoc.Y24;

import z.aoc.util.Day;

public class Y24D03 extends Day {
    String input = getInputString();
    public static int total = 0;
    public static int totalPart2 = 0;
    public static boolean isEnabled = true;
    @Override
    public Object part1() {
        cleanInput(input);
        return total;
    }

    @Override
    public Object part2() {
        processInput(input);
        return totalPart2;
    }
    public static void cleanInput(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (input.startsWith("mul(", i)) {
                int endIndex = input.indexOf(")", i);
                if (endIndex != -1) {
                    String candidate = input.substring(i, endIndex + 1);
                    if (isValidMul(candidate)) {
                        calculate(candidate);
                    }
                }
            }
        }
    }
    public static void processInput(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (input.startsWith("mul(", i)) {
                int endIndex = input.indexOf(")", i);
                if (endIndex != -1) {
                    String candidate = input.substring(i, endIndex + 1);
                    if (isEnabled && isValidMul(candidate)) {
                        calculate2(candidate);
                    }
                }
            } else if (input.startsWith("do()", i)) {
                isEnabled = true; // Enable future `mul` instructions
            } else if (input.startsWith("don't()", i)) {
                isEnabled = false; // Disable future `mul` instructions
            }
        }
    }
    public static boolean isValidMul(String input) {
        // Regex to validate mul(X,Y) where X and Y are 1-3 digit numbers
        return input.matches("mul\\(\\d{1,3},\\d{1,3}\\)");
    }


    public static void calculate(String mulInstruction) {
        String[] numbers = mulInstruction.substring(4, mulInstruction.length() - 1).split(",");
        int x = Integer.parseInt(numbers[0]);
        int y = Integer.parseInt(numbers[1]);
        total += x * y;
    }

    public static void calculate2(String mulInstruction) {
        String[] numbers = mulInstruction.substring(4, mulInstruction.length() - 1).split(",");
        int x = Integer.parseInt(numbers[0]);
        int y = Integer.parseInt(numbers[1]);
        totalPart2 += x * y;
    }

}
