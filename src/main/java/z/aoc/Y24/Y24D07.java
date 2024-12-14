package z.aoc.Y24;

import z.aoc.util.Day;

import java.util.*;

public class Y24D07 extends Day {
    List<String> lines = getInputList();

    @Override
    public Object part1() {
        List<String> input = lines;
        long totalCalibrationResult = 0;

        for (String line : input) {
            String[] parts = line.split(": ");
            long testValue = Long.parseLong(parts[0]);
            String[] numbers = parts[1].split(" ");

            if (isValidEquation(testValue, numbers, Arrays.asList("+", "*"))) {
                totalCalibrationResult += testValue;
            }
        }

        return totalCalibrationResult;
    }

    @Override
    public Object part2() {
        List<String> input = lines;
        long totalCalibrationResult = 0;

        for (String line : input) {
            String[] parts = line.split(": ");
            long testValue = Long.parseLong(parts[0]);
            String[] numbers = parts[1].split(" ");

            if (isValidEquation(testValue, numbers, Arrays.asList("+", "*", "||"))) {
                totalCalibrationResult += testValue;
            }
        }

        return totalCalibrationResult;
    }

    private boolean isValidEquation(long testValue, String[] numbers, List<String> operators) {
        List<String[]> operatorCombinations = generateOperatorCombinations(numbers.length - 1, operators);

        for (String[] operatorCombination : operatorCombinations) {
            long result = Long.parseLong(numbers[0]);
            for (int i = 1; i < numbers.length; i++) {
                String operator = operatorCombination[i - 1];
                long num = Long.parseLong(numbers[i]);

                switch (operator) {
                    case "+" -> result += num;
                    case "*" -> result *= num;
                    case "||" -> result = Long.parseLong(result + Long.toString(num));
                }
            }

            if (result == testValue) {
                return true;
            }
        }

        return false;
    }

    private List<String[]> generateOperatorCombinations(int numOperators, List<String> operators) {
        List<String[]> combinations = new ArrayList<>();
        generateOperatorCombinationsRecursive(new String[numOperators], 0, operators, combinations);
        return combinations;
    }

    private void generateOperatorCombinationsRecursive(String[] current, int index, List<String> operators, List<String[]> combinations) {
        if (index == current.length) {
            combinations.add(current.clone());
            return;
        }

        for (String operator : operators) {
            current[index] = operator;
            generateOperatorCombinationsRecursive(current, index + 1, operators, combinations);
        }
    }
}
