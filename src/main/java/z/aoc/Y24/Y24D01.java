package z.aoc.Y24;

import z.aoc.util.Day;

import java.util.ArrayList;
import java.util.List;

public class Y24D01 extends Day {
    List<String> lines = getInputList();
    public static int difference;
    public static int similarity;

    @Override
    public Object part1() {
        List<Integer> team1 = new ArrayList<>();
        List<Integer> team2 = new ArrayList<>();
        for (String line : lines) {
            String[] values = line.split("   ");
            team1.add(Integer.parseInt(values[0]));
            team2.add(Integer.parseInt(values[1]));
            //System.out.println(values[0] + " " + values[1]);
        }
        sortTeams(team1, team2);

        findDifferencep1(team1, team2);
        findDifferencep2(team1, team2);
        return difference;
    }

    @Override
    public Object part2() {
        return similarity;
    }

    //sort team1 and team2 from lowest to highest
    public static void sortTeams(List<Integer> team1, List<Integer> team2) {
        team1.sort(Integer::compareTo);
        // equal to team1.sort((a, b) -> a.compareTo(b));
        team2.sort(Integer::compareTo);
        // equal to team2.sort((a, b) -> a.compareTo(b));
    }

    //method to loop through both arrays, compare the values and find the difference between them
    public static void findDifferencep1(List<Integer> team1, List<Integer> team2) {
        for (int i = 0; i < team1.size(); i++) {
            difference += Math.abs(team1.get(i) - team2.get(i));
        }
    }
    public static void findDifferencep2(List<Integer> left, List<Integer> right) {
        for (Integer integer : left) {
            int count = 0;
            for (Integer value : right) {
                if (integer.equals(value)) {
                    count++;
                }
            }
            similarity += (integer * count);
        }
    }
}
