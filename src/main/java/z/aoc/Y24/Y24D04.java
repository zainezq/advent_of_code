package z.aoc.Y24;

import z.aoc.util.Day;

public class Y24D04 extends Day {
    static String word = "XMAS";
    char[][] grid = loadInputAs2DArray();
    static int count = 0;
    static int xmasCount = 0;

    @Override
    public Object part1() {
        count = countWordOccurrences(grid, word);
        return count;
    }

    @Override
    public Object part2() {
        // Step 1: Replace 'X' with '.'
        markXAsDot(grid);
        // Step 2: Count X-MAS patterns
        xmasCount = countXMASPatterns(grid);
        return xmasCount;
    }
    public static int countWordOccurrences(char[][] grid, String word) {
        int rows = grid.length;
        int cols = grid[0].length;
        int count = 0;

        // Directions: right, left, down, up, diagonals
        int[][] directions = {
                {0, 1}, {0, -1}, {1, 0}, {-1, 0},
                {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
        };

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                for (int[] dir : directions) {
                    int dx = dir[0], dy = dir[1];
                    if (isWordFound(grid, word, row, col, dx, dy)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
    private static boolean isWordFound(char[][] grid, String word, int row, int col, int dx, int dy) {
        int wordLength = word.length();
        int rows = grid.length;
        int cols = grid[0].length;

        for (int i = 0; i < wordLength; i++) {
            int newRow = row + i * dx;
            int newCol = col + i * dy;

            if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols || grid[newRow][newCol] != word.charAt(i)) {
                return false;
            }
        }
        return true;
    }
    // Step 1: Mark all 'X' characters as '.'
    public static void markXAsDot(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 'X') {
                    grid[i][j] = '.';
                }
            }
        }
    }

    // Step 2: Count X-MAS patterns around each 'A'
    public static int countXMASPatterns(char[][] grid) {
        int count = 0;
        int rows = grid.length;
        int cols = grid[0].length;
        // Loop through every cell to find 'A'
        for (int i = 1; i < rows - 1; i++) { // Avoid borders
            for (int j = 1; j < cols - 1; j++) {
                if (grid[i][j] == 'A' && isXMASAroundA(grid, i, j)) {
                    count++;
                }
            }
        }
        return count;
    }
    // Function to check for X-MAS pattern around 'A'
    public static boolean isXMASAroundA(char[][] grid, int i, int j) {
        // Check top-left to bottom-right diagonal (M.S)
        boolean topLeft = (grid[i - 1][j - 1] == 'M' && grid[i + 1][j + 1] == 'S') || (grid[i - 1][j - 1] == 'S' && grid[i + 1][j + 1] == 'M');
        // Check top-right to bottom-left diagonal (M.S)
        boolean topRight = (grid[i - 1][j + 1] == 'M' && grid[i + 1][j - 1] == 'S') || (grid[i - 1][j + 1] == 'S' && grid[i + 1][j - 1] == 'M');
        // Return true only if both diagonals form an 'X-MAS'
        return topLeft && topRight;
    }
}
