package z.aoc;

import z.aoc.Y24.*;
import z.aoc.util.Day;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        List<Day> days = new ArrayList<>();
        days.add(new Y24D01());
        days.add(new Y24D02());
        days.add(new Y24D03());
        days.add(new Y24D04());
        days.add(new Y24D05());
        days.add(new Y24D06());
        days.add(new Y24D07());
        days.add(new Y24D08());
        days.add(new Y24D09());
        days.add(new Y24D10());
        days.add(new Y24D11());
        days.add(new Y24D12());
        days.add(new Y24D13());
        days.add(new Y24D14());
        days.add(new Y24D15());
        days.add(new Y24D16());
        days.add(new Y24D17());
        days.add(new Y24D18());
        days.add(new Y24D19());

        for (Day day : days) {
            day.run();
        }

        long endTime = System.currentTimeMillis();
        long totalRuntime = endTime - startTime;
        System.out.println("Total runtime: " + totalRuntime / 1000.0 + " seconds");
    }
}