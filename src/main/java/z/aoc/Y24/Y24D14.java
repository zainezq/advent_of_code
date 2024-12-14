package z.aoc.Y24;

import z.aoc.util.Day;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Y24D14 extends Day {


    @Override
    public Object part1() {
        List<PointAndVelocity> pvs = getPointAndVelocities();
        //  robot movement for 100 seconds
        for (int t = 0; t < 100; t++) {
            for (PointAndVelocity pav : pvs) {
                pav.setPX(wrap(pav.getPX() + pav.getVX(), 101));  // Wrap around horizontally
                pav.setPY(wrap(pav.getPY() + pav.getVY(), 103));  // Wrap around vertically
            }
        }

        // Calculate the safety factor
        int[] quadrantCounts = new int[4];  // [top-left, top-right, bottom-left, bottom-right]

        for (PointAndVelocity pav : pvs) {
            int x = pav.getPX();
            int y = pav.getPY();

            // Exclude robots in the middle
            if (x == 50 || y == 51) continue;  // Robot is in the middle

            // Assign quadrants
            if (x < 50 && y < 51) {
                quadrantCounts[0]++;  // Top-left
            } else if (x >= 50 && y < 51) {
                quadrantCounts[1]++;  // Top-right
            } else if (x < 50 && y >= 51) {
                quadrantCounts[2]++;  // Bottom-left
            } else if (x >= 50 && y >= 51) {
                quadrantCounts[3]++;  // Bottom-right
            }
        }

        // Calculate safety factor (product of robot counts in quadrants)
        int safetyFactor = 1;
        for (int count : quadrantCounts) {
            safetyFactor *= count;
        }

        return safetyFactor;

    }
    public static int wrap(int value, int max) {
        return ((value % max) + max) % max;
    }

    @Override
    public Object part2() {
        List<int[][]> inputs = parseInput(getInputList());
        int[] tileDimensions = { 101, 103 };
        int[][] grid = initializeGrid(tileDimensions[0], tileDimensions[1]);
        int step = 1;
        int requiredConsecutive = 10;

        while (step < 1_000_000_000) {
            int[][] nextGrid = initializeGrid(tileDimensions[0], tileDimensions[1]);
            Map<Integer, List<Integer>> columns = new HashMap<>();

            for (int[][] data : inputs) {
                int[] newPosition = calculateNewPosition(data[0], data[1], step, tileDimensions);
                nextGrid[newPosition[0]][newPosition[1]]++;
                columns.putIfAbsent(newPosition[1], new ArrayList<>());
                columns.get(newPosition[1]).add(newPosition[0]);
            }

            for (List<Integer> columnPositions : columns.values()) {
                if (hasConsecutiveInRow(columnPositions, requiredConsecutive)) {
                    return step;
                }
            }

            grid = nextGrid;
            step++;
        }

        return -1;

    }


    private List<PointAndVelocity> getPointAndVelocities() {
        List<PointAndVelocity> pvs = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fetchFilePath()));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                PointAndVelocity pav = new PointAndVelocity();
                pav.setPX(Integer.parseInt(parts[0].split(",")[0].replace("p=", "")));
                pav.setPY(Integer.parseInt(parts[0].split(",")[1].replace("p=", "")));
                pav.setVX(Integer.parseInt(parts[1].split(",")[0].replace("v=", "")));
                pav.setVY(Integer.parseInt(parts[1].split(",")[1].replace("v=", "")));
                pvs.add(pav);
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return pvs;
    }
    private List<int[][]> parseInput(List<String> inputList) {
        return inputList.stream()
                .map(x -> x.replace(",", " ").replace("=", " ").split(" "))
                .map(parts -> new int[][] {
                        { Integer.parseInt(parts[1]), Integer.parseInt(parts[2]) },
                        { Integer.parseInt(parts[4]), Integer.parseInt(parts[5]) }
                })
                .collect(Collectors.toList());
    }
    private int[][] initializeGrid(int rows, int cols) {
        return new int[rows][cols];
    }
    private int[] calculateNewPosition(int[] position, int[] velocity, int step, int[] tileDimensions) {
        return new int[] {
                (position[0] + step * (tileDimensions[0] + velocity[0])) % tileDimensions[0],
                (position[1] + step * (tileDimensions[1] + velocity[1])) % tileDimensions[1]
        };
    }


    private boolean hasConsecutiveInRow(List<Integer> positions, int requiredConsecutive) {
        Collections.sort(positions);
        int consecutiveCount = 0;

        for (int i = 1; i < positions.size(); i++) {
            if (positions.get(i) - positions.get(i - 1) == 1) {
                consecutiveCount++;
                if (consecutiveCount >= requiredConsecutive) {
                    return true;
                }
            } else {
                consecutiveCount = 0;
            }
        }
        return false;
    }

    public static class PointAndVelocity {
        private int PX;
        private int PY;
        private int VX;
        private int VY;
        // Getters and setters
        public int getVX() { return VX; }
        public void setVX(int vX) { VX = vX; }
        public int getVY() { return VY; }
        public void setVY(int vY) { VY = vY; }
        public int getPX() { return PX; }
        public void setPX(int pX) { PX = pX; }
        public int getPY() { return PY; }
        public void setPY(int pY) { PY = pY; }
    }

}
