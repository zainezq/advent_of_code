package z.aoc.Y24;

import z.aoc.util.Day;

import java.util.*;

public class Y24D08 extends Day {
    @Override
    public Object part1() {

        List<String> map = getInputList();

        return countUniqueAntinodes(map);

    }

    @Override
    public Object part2() {

        List<String> map = getInputList();
        return countUniqueAntinodespart2(map);


    }

    public static int countUniqueAntinodes(List<String> map) {
        int rows = map.size();
        int cols = map.get(0).length();

        // Store antenna locations by their frequency
        Map<Character, List<int[]>> antennas = new HashMap<>();

        // Parse the map to find antennas
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                char ch = map.get(r).charAt(c);
                if (Character.isLetterOrDigit(ch)) {
                    antennas.putIfAbsent(ch, new ArrayList<>());
                    antennas.get(ch).add(new int[]{r, c});
                }
            }
        }

        // Set to store unique antinode locations
        Set<String> antinodes = new HashSet<>();

        // Process each frequency
        for (Map.Entry<Character, List<int[]>> entry : antennas.entrySet()) {
            List<int[]> locations = entry.getValue();

            // Check all pairs of antennas with the same frequency
            for (int i = 0; i < locations.size(); i++) {
                for (int j = i + 1; j < locations.size(); j++) {
                    int[] a = locations.get(i);
                    int[] b = locations.get(j);

                    // Calculate midpoint and vector differences
                    int dr = b[0] - a[0];
                    int dc = b[1] - a[1];

                    // Check if one antenna is twice as far as the other
                    int antinodeRow1 = a[0] - dr;
                    int antinodeCol1 = a[1] - dc;
                    int antinodeRow2 = b[0] + dr;
                    int antinodeCol2 = b[1] + dc;

                    // Add antinodes if within bounds
                    if (isWithinBounds(antinodeRow1, antinodeCol1, rows, cols)) {
                        antinodes.add(antinodeRow1 + "," + antinodeCol1);
                    }
                    if (isWithinBounds(antinodeRow2, antinodeCol2, rows, cols)) {
                        antinodes.add(antinodeRow2 + "," + antinodeCol2);
                    }
                }
            }
        }

        return antinodes.size();
    }
    public static int countUniqueAntinodespart2(List<String> map) {
        int rows = map.size();
        int cols = map.get(0).length();

        // Store antenna locations by their frequency
        Map<Character, List<int[]>> antennas = new HashMap<>();

        // Parse the map to find antennas
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                char ch = map.get(r).charAt(c);
                if (Character.isLetterOrDigit(ch)) {
                    antennas.putIfAbsent(ch, new ArrayList<>());
                    antennas.get(ch).add(new int[]{r, c});
                }
            }
        }

        // Set to store unique antinode locations
        Set<String> antinodes = new HashSet<>();

        // Process each frequency
        for (Map.Entry<Character, List<int[]>> entry : antennas.entrySet()) {
            List<int[]> locations = entry.getValue();

            // Check all pairs of antennas with the same frequency
            for (int i = 0; i < locations.size(); i++) {
                for (int j = i + 1; j < locations.size(); j++) {
                    int[] a = locations.get(i);
                    int[] b = locations.get(j);

                    // Calculate vector differences
                    int dr = b[0] - a[0];
                    int dc = b[1] - a[1];

                    // Simplify the direction vector
                    int gcd = gcd(Math.abs(dr), Math.abs(dc));
                    dr /= gcd;
                    dc /= gcd;

                    // Traverse along the line and add all points
                    int row = a[0], col = a[1];
                    while (isWithinBounds(row, col, rows, cols)) {
                        antinodes.add(row + "," + col);
                        row += dr;
                        col += dc;
                    }

                    row = a[0] - dr;
                    col = a[1] - dc;
                    while (isWithinBounds(row, col, rows, cols)) {
                        antinodes.add(row + "," + col);
                        row -= dr;
                        col -= dc;
                    }
                }
            }

            // Each antenna is also an antinode
            for (int[] antenna : locations) {
                antinodes.add(antenna[0] + "," + antenna[1]);
            }
        }

        return antinodes.size();
    }


    private static boolean isWithinBounds(int row, int col, int rows, int cols) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }
    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

}
