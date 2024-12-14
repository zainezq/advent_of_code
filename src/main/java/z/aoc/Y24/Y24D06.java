package z.aoc.Y24;

import z.aoc.util.Day;

import java.util.*;

public class Y24D06 extends Day {
    List<String> lines = getInputList();

    @Override
    public Object part1() {
        // Parse the map
        char[][] map = parseMap(lines);

        // Initialize guard position and facing direction
        int[] guardPosition = findGuardPosition(map);
        int guardRow = guardPosition[0];
        int guardCol = guardPosition[1];
        char guardFacing = (char) guardPosition[2];

        // Directions and movement logic
        Map<Character, int[]> directions = getDirections();
        Map<Character, Character> turnRight = getTurnRightMap();

        Set<String> visited = new HashSet<>();
        visited.add(guardRow + "," + guardCol);

        while (true) {
            int[] move = directions.get(guardFacing);
            int nextRow = guardRow + move[0];
            int nextCol = guardCol + move[1];

            if (nextRow < 0 || nextRow >= map.length || nextCol < 0 || nextCol >= map[0].length) {
                break; // Guard leaves the map
            }

            if (map[nextRow][nextCol] == '#') {
                // Turn right
                guardFacing = turnRight.get(guardFacing);
            } else {
                // Move forward
                guardRow = nextRow;
                guardCol = nextCol;
                visited.add(guardRow + "," + guardCol);
            }
        }

        return visited.size();
    }

    @Override
    public Object part2() {
        // Parse the map
        char[][] map = parseMap(lines);

        // Initialize guard position and facing direction
        int[] guardPosition = findGuardPosition(map);
        int guardRow = guardPosition[0];
        int guardCol = guardPosition[1];
        char guardFacing = (char) guardPosition[2];

        // Directions and movement logic
        Map<Character, int[]> directions = getDirections();
        Map<Character, Character> turnRight = getTurnRightMap();

        Set<String> validObstructions = new HashSet<>();

        // Iterate over all potential obstruction positions
        for (int r = 0; r < map.length; r++) {
            for (int c = 0; c < map[0].length; c++) {
                if (map[r][c] == '.' && !(r == guardRow && c == guardCol)) {
                    // Temporarily place an obstruction
                    map[r][c] = '#';

                    // Simulate the guard's patrol with the obstruction
                    if (causesLoop(map, guardRow, guardCol, guardFacing, directions, turnRight)) {
                        validObstructions.add(r + "," + c);
                    }

                    // Remove the obstruction
                    map[r][c] = '.';
                }
            }
        }

        return validObstructions.size();
    }

    private char[][] parseMap(List<String> input) {
        int rows = input.size();
        int cols = input.getFirst().length();
        char[][] map = new char[rows][cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                map[r][c] = input.get(r).charAt(c);
            }
        }

        return map;
    }

    private int[] findGuardPosition(char[][] map) {
        for (int r = 0; r < map.length; r++) {
            for (int c = 0; c < map[0].length; c++) {
                if ("^v<>".indexOf(map[r][c]) != -1) {
                    char guardFacing = map[r][c];
                    map[r][c] = '.'; // Clear the guard's position for tracking
                    return new int[]{r, c, guardFacing};
                }
            }
        }
        throw new IllegalStateException("Guard not found on the map");
    }

    private Map<Character, int[]> getDirections() {
        return Map.of(
                '^', new int[]{-1, 0},
                'v', new int[]{1, 0},
                '<', new int[]{0, -1},
                '>', new int[]{0, 1}
        );
    }

    private Map<Character, Character> getTurnRightMap() {
        return Map.of(
                '^', '>',
                '>', 'v',
                'v', '<',
                '<', '^'
        );
    }

    private boolean causesLoop(char[][] map, int guardRow, int guardCol, char guardFacing,
                               Map<Character, int[]> directions, Map<Character, Character> turnRight) {
        Set<String> seenStates = new HashSet<>();

        while (true) {
            String state = guardRow + "," + guardCol + "," + guardFacing;
            if (seenStates.contains(state)) {
                return true; // Loop detected
            }
            seenStates.add(state);

            int[] move = directions.get(guardFacing);
            int nextRow = guardRow + move[0];
            int nextCol = guardCol + move[1];

            if (nextRow < 0 || nextRow >= map.length || nextCol < 0 || nextCol >= map[0].length) {
                return false; // Guard leaves the map
            }

            if (map[nextRow][nextCol] == '#') {
                // Turn right
                guardFacing = turnRight.get(guardFacing);
            } else {
                // Move forward
                guardRow = nextRow;
                guardCol = nextCol;
            }
        }
    }
}
