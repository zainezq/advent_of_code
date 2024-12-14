import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import z.aoc.Y24.*;
import z.aoc.util.Day;

import java.math.BigInteger;

public class AOCTest {

    // Timing measurement
    private long startTime;

    private void logTime(String testName) {
        long duration = System.nanoTime() - startTime;
        double durationInMs = duration / 1e6;  // Convert to milliseconds
        System.out.println(testName + " took " + durationInMs + " ms");
    }

    @Test
    public void testDay01() {
        startTime = System.nanoTime();  // Start timing
        Day day1 = new Y24D01();
        Assertions.assertEquals(1580061, day1.part1()); // Expected result for part 1
        Assertions.assertEquals(23046913, day1.part2()); // Expected result for part 2
        logTime("testDay1");  // Log time for this test
    }

    @Test
    public void testDay02() {
        startTime = System.nanoTime();
        Day day2 = new Y24D02();
        Assertions.assertEquals(559, day2.part1());  // Expected result for part 1
        Assertions.assertEquals(601, day2.part2()); // Expected result for part 2
        logTime("testDay2");
    }

    @Test
    public void testDay03() {
        startTime = System.nanoTime();
        Day day3 = new Y24D03();
        Assertions.assertEquals(162813399, day3.part1());  // Expected result for part 1
        Assertions.assertEquals(53783319, day3.part2()); // Expected result for part 2
        logTime("testDay3");
    }

    @Test
    public void testDay04() {
        startTime = System.nanoTime();
        Day day4 = new Y24D04();
        Assertions.assertEquals(2573, day4.part1());  // Expected result for part 1
        Assertions.assertEquals(1850, day4.part2()); // Expected result for part 2
        logTime("testDay4");
    }

    @Test
    public void testDay05() {
        startTime = System.nanoTime();
        Day day5 = new Y24D05();
        Assertions.assertEquals(4569, day5.part1());  // Expected result for part 1
        Assertions.assertEquals(6456, day5.part2()); // Expected result for part 2
        logTime("testDay5");
    }

    @Test
    public void testDay06() {
        startTime = System.nanoTime();
        Day day6 = new Y24D06();
        Assertions.assertEquals(5331, day6.part1());  // Expected result for part 1
        Assertions.assertEquals(1812, day6.part2()); // Expected result for part 2
        logTime("testDay6");
    }

    @Test
    public void testDay07() {
        startTime = System.nanoTime();
        Day day7 = new Y24D07();
        Assertions.assertEquals(267566105056L, day7.part1());  // Expected result for part 1
        Assertions.assertEquals(116094961956019L, day7.part2()); // Expected result for part 2
        logTime("testDay7");
    }

    @Test
    public void testDay08() {
        startTime = System.nanoTime();
        Day day8 = new Y24D08();
        Assertions.assertEquals(354, day8.part1());  // Expected result for part 1
        Assertions.assertEquals(1263, day8.part2()); // Expected result for part 2
        logTime("testDay8");
    }

    @Test
    public void testDay09() {
        startTime = System.nanoTime();
        Day day9 = new Y24D09();
        Assertions.assertEquals(new BigInteger("6341711060162"), day9.part1()); // Expect BigInteger for part 1
        Assertions.assertEquals(new BigInteger("6377400869326"), day9.part2()); // Expect BigInteger for part 2
        logTime("testDay9");
    }

    @Test
    public void testDay10() {
        startTime = System.nanoTime();
        Day day10 = new Y24D10();
        Assertions.assertEquals(786L, day10.part1());  // Expected result for part 1
        Assertions.assertEquals(1722L, day10.part2()); // Expected result for part 2
        logTime("testDay10");
    }

    @Test
    public void testDay11() {
        startTime = System.nanoTime();
        Day day11 = new Y24D11();
        Assertions.assertEquals(188902L, day11.part1());  // Expected result for part 1
        Assertions.assertEquals(223894720281135L, day11.part2()); // Expected result for part 2
        logTime("testDay11");
    }

    @Test
    public void testDay12() {
        startTime = System.nanoTime();
        Day day12 = new Y24D12();
        Assertions.assertEquals(1452678, day12.part1());  // Expected result for part 1
        Assertions.assertEquals(873584, day12.part2()); // Expected result for part 2
        logTime("testDay12");
    }

    @Test
    public void testDay13() {
        startTime = System.nanoTime();
        Day day13 = new Y24D13();
        Assertions.assertEquals(27105L, day13.part1());  // Expected result for part 1
        Assertions.assertEquals(101726882250942L, day13.part2()); // Expected result for part 2
        logTime("testDay13");
    }


    @Test
    public void testDay14() {
        startTime = System.nanoTime();
        Day day14 = new Y24D14();
        Assertions.assertEquals(229980828, day14.part1());  // Expected result for part 1
        Assertions.assertEquals(7132, day14.part2()); // Expected result for part 2
        logTime("testDay14");
    }

        /*
    @Test
    public void testDay15() {
        startTime = System.nanoTime();
        Day day15 = new Y24D15();
        Assertions.assertEquals(0, day15.part1());  // Expected result for part 1
        Assertions.assertEquals(0, day15.part2()); // Expected result for part 2
        logTime("testDay15");
    }
    */

/*
    @Test
    public void timing(){
        long totalStartTime = System.nanoTime();
        // Run all tests manually
        AOCTest test = new AOCTest();
        test.testDay01();
        test.testDay02();
        test.testDay03();
        test.testDay04();
        test.testDay05();
        test.testDay06();
        test.testDay07();
        test.testDay08();
        test.testDay09();
        test.testDay10();
        test.testDay11();
        test.testDay12();
        test.testDay13();
        // test.testDay14();
        long totalDuration = System.nanoTime() - totalStartTime;
        double totalDurationInMs = totalDuration / 1e6;  // Convert to milliseconds
        System.out.println("Total time for all tests: " + totalDurationInMs + " ms");

    }*/

    public static void main(String[] args) {

        long totalStartTime = System.nanoTime();
        // Run all tests manually
        AOCTest test = new AOCTest();
        test.testDay01();
        test.testDay02();
        test.testDay03();
        test.testDay04();
        test.testDay05();
        test.testDay06();
        test.testDay07();
        test.testDay08();
        test.testDay09();
        test.testDay10();
        test.testDay11();
        test.testDay12();
        test.testDay13();
        test.testDay14();

        long totalDuration = System.nanoTime() - totalStartTime;
        double totalDurationInMs = totalDuration / 1e6;  // Convert to milliseconds
        System.out.println("Total time for all tests: " + totalDurationInMs + " ms");
    }
}
