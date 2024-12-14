package z.aoc;
import z.aoc.Y24.*;
import z.aoc.util.Day;

public class CurrentDay {
    static int year = 24;
    static int day = 14;

    public static void main(String[] args) {
        Day dayInstance = getDayInstance(year, day);
        dayInstance.run();
    }

    private static Day getDayInstance(int year, int day) {
        if (year == 24) {
            return switch (day) {
                case 1 -> new Y24D01();
                case 2 -> new Y24D02();
                case 3 -> new Y24D03();
                case 4 -> new Y24D04();
                case 5 -> new Y24D05();
                case 6 -> new Y24D06();
                case 7 -> new Y24D07();
                case 8 -> new Y24D08();
                case 9 -> new Y24D09();
                case 10 -> new Y24D10();
                case 11 -> new Y24D11();
                case 12 -> new Y24D12();
                case 13 -> new Y24D13();
                case 14 -> new Y24D14();
                case 15 -> new Y24D15();
                default -> throw new RuntimeException("No implementation for day " + day);
            };
        } else {
            throw new RuntimeException("No implementation for year " + year);
        }
    }
}
