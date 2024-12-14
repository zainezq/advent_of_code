package z.aoc.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Day {

    int year;
    String day;

    public Day() {
        String name = this.getClass().getSimpleName().substring(1);
        String[] spl = name.split("D");
        year = Integer.parseInt(spl[0]);
        day = spl[1];
    }

    public void run() {
        System.out.println("Year: " + year + " Day: " + day );
        long p1TimeStart = System.nanoTime();
        Object p1 = part1();
        if (p1 == null)
            p1 = "null";
        double p1Time = (System.nanoTime() - p1TimeStart) / 1e6;
        System.out.println("Part1: " + p1 + " \t " + p1Time + " ms");

        long p2TimeStart = System.nanoTime();
        Object p2 = part2();
        if (p2 == null)
            p2 = "null";
        double p2Time = (System.nanoTime() - p2TimeStart) / 1e6;
        System.out.println("Part2: " + p2 + " \t " + p2Time + " ms");
        System.out.println();
    }

    public abstract Object part1();

    public abstract Object part2();

    public String getFileString() {
        try {
            return Files.readString(new File(fetchFilePath()).toPath());
        } catch (IOException e) {
            Logger.getLogger(Day.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    public String getInputString() {
        StringBuilder str = new StringBuilder();
        try {
            BufferedReader brTest = new BufferedReader(new FileReader(fetchFilePath()));
            String line;
            while ((line = brTest.readLine()) != null) {
                str.append(line);
            }
        } catch (IOException e) {
            Logger.getLogger(Day.class.getName()).log(Level.SEVERE, null, e);
        }
        return str.toString();
    }

    public List<String> getInputList() {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(new File(fetchFilePath()).toPath(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            Logger.getLogger(Day.class.getName()).log(Level.SEVERE, null, e);
        }
        return lines;
    }
    // Function to load input and store as a 2D array
    public char[][] loadInputAs2DArray() {
        char[][] grid = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fetchFilePath()));
            String line;
            int rows = 0;
            // First pass to count rows
            while (reader.readLine() != null) {
                rows++;
            }
            reader.close();
            // Create 2D array
            grid = new char[rows][];
            // Second pass to fill the array
            reader = new BufferedReader(new FileReader(fetchFilePath()));
            int row = 0;
            while ((line = reader.readLine()) != null) {
                grid[row] = line.toCharArray();
                row++;
            }
            reader.close();
        } catch (IOException e) {
            Logger.getLogger(Day.class.getName()).log(Level.SEVERE, null, e);
        }

        return grid;

    }

    protected String fetchFilePath() {
        return "input/" + year + "/" + day;
    }

}
