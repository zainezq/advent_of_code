package z.aoc.Y24;

import z.aoc.util.Day;

import java.util.List;

public class Y24D02 extends Day {
    static int count = 0;
    static int correct = 0;
    List<String> lines = getInputList();

    @Override
    public Object part1() {
        checkReport(lines);
        return count;
    }

    @Override
    public Object part2() {
        for (String line : lines){
            boolean flag = normalCheck(line);
            if (flag){
                boolean duplicatedCheck = checkLine(line);
                if (duplicatedCheck){
                    correct++;
                }
            } else {
                correct++;
            }
        }
        return correct;
    }
    public static void checkReport(List<String> lines) {
        for (String line : lines) {
            //get each number
            String[] values = line.split(" ");
            // check if all are increasing or decreasing
            boolean increasing = true;
            boolean decreasing = true;
            boolean adjacent = true;
            for (int i = 0; i < values.length - 1; i ++){
                if (Integer.parseInt(values[i]) < Integer.parseInt(values[i + 1])){
                    decreasing = false;
                }
                if (Integer.parseInt(values[i]) > Integer.parseInt(values[i + 1])){
                    increasing = false;
                }
                if (Integer.parseInt(values[i]) - Integer.parseInt(values[i+1]) == 1  ||
                        Integer.parseInt(values[i]) - Integer.parseInt(values[i+1]) == -1  ||
                        Integer.parseInt(values[i]) - Integer.parseInt(values[i+1]) == 2  ||
                        Integer.parseInt(values[i]) - Integer.parseInt(values[i+1]) == -2  ||
                        Integer.parseInt(values[i]) - Integer.parseInt(values[i+1]) == 3  ||
                        Integer.parseInt(values[i]) - Integer.parseInt(values[i+1]) == -3 ){
                } else {
                    adjacent = false;
                }
            }
            if ((increasing || decreasing) && adjacent){
                count++;
            }
        }
    }
    // Function to check if a line can become safe by removing one element
    public static boolean checkLine(String input){
        String[] values = input.split(" ");
        // Try removing one element and check if the remaining sequence is safe
        for (int i = 0; i < values.length; i++) {
            // Create a new array without the i-th element
            String[] newArray = new String[values.length - 1];
            for (int j = 0, k = 0; j < values.length; j++) {
                if (j != i) {
                    newArray[k++] = values[j];
                }
            }
            // Check if the array without this element is safe
            if (!normalCheck(String.join(" ", newArray))) {
                return true; // It becomes safe after removing one level
            }
        }
        return false; // If no removal makes it safe
    }

    public static boolean normalCheck(String input){
        boolean flag = true;
        String[] values = input.split(" ");
        // check if all are increasing or decreasing
        boolean increasing = true;
        boolean decreasing = true;
        boolean adjacent = true;
        for (int i = 0; i < values.length - 1; i ++){
            if (Integer.parseInt(values[i]) < Integer.parseInt(values[i + 1])){
                decreasing = false;
            }
            if (Integer.parseInt(values[i]) > Integer.parseInt(values[i + 1])){
                increasing = false;
            }
            if (Integer.parseInt(values[i]) - Integer.parseInt(values[i+1]) == 1  ||
                    Integer.parseInt(values[i]) - Integer.parseInt(values[i+1]) == -1  ||
                    Integer.parseInt(values[i]) - Integer.parseInt(values[i+1]) == 2  ||
                    Integer.parseInt(values[i]) - Integer.parseInt(values[i+1]) == -2  ||
                    Integer.parseInt(values[i]) - Integer.parseInt(values[i+1]) == 3  ||
                    Integer.parseInt(values[i]) - Integer.parseInt(values[i+1]) == -3 ){
            } else {
                adjacent = false;
            }
        }
        if ((increasing || decreasing) && adjacent){
            flag = false;
        }
        return flag;
    }
}
