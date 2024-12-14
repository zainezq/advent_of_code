package z.aoc.Y24;

import z.aoc.util.Day;

import java.util.*;

public class Y24D10 extends Day {
    List<String> lines = getInputList();

    @Override
    public Object part1() {
        int height = lines.size();
        int width = lines.getFirst().length();

        int[] map = new int[width * height];

        int i = 0;
        for (String line : lines) {
            if (line.isBlank()) continue;
            for (String character : line.trim().split("")) {
                map[i] = Integer.parseInt(character);
                i++;
            }
        }

        long count = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Set<Point> set = countTrails(map, x, y, width, height,0);
                count += set.size();
            }
        }
        return count;
    }

    @Override
    public Object part2() {
        int height = lines.size();
        int width = lines.getFirst().length();

        int[] map = new int[width * height];

        int i = 0;
        for (String line : lines) {
            if (line.isBlank()) continue;
            for (String character : line.trim().split("")) {
                map[i] = Integer.parseInt(character);
                i++;
            }
        }

        long count = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Map<Point, Integer> res = countTrails2(map, x, y, width, height,0);
                for (Map.Entry<Point, Integer> entry : res.entrySet()) {
                    count += entry.getValue();
                }
            }
        }
        return count;
    }

    private static Set<Point> countTrails(int[] map, int x, int y, int width, int height, int val) {
        if (x >= width) return new HashSet<>();
        if (y >= height) return new HashSet<>();
        if (x < 0) return new HashSet<>();
        if (y < 0) return new HashSet<>();

        if (map[y * width + x] != val) return new HashSet<>();
        if (val == 9) {
            Set<Point> newSet = new HashSet<>();
            newSet.add(new Point(9, x, y));
            return newSet;
        }

        Set<Point> newSet = new HashSet<>();
        newSet.addAll(countTrails(map, x + 1, y, width, height, val + 1));
        newSet.addAll(countTrails(map, x - 1, y, width, height, val + 1));
        newSet.addAll(countTrails(map, x, y + 1, width, height, val + 1));
        newSet.addAll(countTrails(map, x, y - 1, width, height, val + 1));

        return newSet;
    }
    public static class Point {
        private int rating;
        private final int x;
        private final int y;

        public Point(int rating, int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getRating() {
            return rating;
        }

        public void incRating() {
            rating++;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
}
    private static Map<Point, Integer> countTrails2(int[] map, int x, int y, int width, int height, int val) {
        if (x >= width) return new HashMap<>();
        if (y >= height) return new HashMap<>();
        if (x < 0) return new HashMap<>();
        if (y < 0) return new HashMap<>();

        if (map[y * width + x] != val) return new HashMap<>();
        if (val == 9) {
            Map<Point, Integer> newSet = new HashMap<>();
            newSet.put(new Point(1, x, y), 1);
            return newSet;
        }

        Map<Point, Integer> newSet = new HashMap<>();
        checkDirection(map, x + 1, y, width, height, val, newSet);
        checkDirection(map, x - 1, y, width, height, val, newSet);
        checkDirection(map, x, y + 1, width, height, val, newSet);
        checkDirection(map, x, y - 1, width, height, val, newSet);

        return newSet;
    }

    private static void checkDirection(int[] map, int x, int y, int width, int height, int val, Map<Point, Integer> newSet) {
        Map<Point, Integer> res = countTrails2(map, x, y, width, height, val + 1);
        for (Point p : res.keySet()) {
            if (newSet.containsKey(p)) {
                newSet.put(p, newSet.get(p) + res.get(p));
            } else {
                newSet.put(p, res.get(p));
            }
        }
    }
}
