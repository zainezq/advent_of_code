package z.aoc.Y24;

import z.aoc.util.Day;

import java.util.*;

public class Y24D11 extends Day {

    Map<String, Long> memo = new HashMap<>();
    String[] in = getInputString().split(" ");

    @Override
    public Object part1() {
        return solve(25);
    }

    @Override
    public Object part2() {
        return solve(75);
    }

    long solve(long startVal) {
        long ret = 0;
        for (var s : in) {
            ret += fetchAmt(Long.parseLong(s), startVal);
        }
        return ret;
    }

    long max = 0;

    long fetchAmt(long stoneValue, long remainingBlinks) {
        String key = stoneValue + ":" + remainingBlinks;

        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        if (remainingBlinks == 0) {
            return 1L;
        } else {
            if (stoneValue == 0) {
                long result = fetchAmt(stoneValue + 1, remainingBlinks - 1);
                memo.put(key, result);
                return result;
            } else if (("" + stoneValue).length() % 2 == 0) {
                if (stoneValue > max) {
                    max = stoneValue;
                    //System.out.println(max);
                }
                String s = "" + stoneValue;
                long left = Long.parseLong(s.substring(0, s.length() / 2));
                long right = Long.parseLong(s.substring(s.length() / 2));

                long leftResult = fetchAmt(left, remainingBlinks - 1);
                long rightResult = fetchAmt(right, remainingBlinks - 1);

                long result = leftResult + rightResult;
                memo.put(key, result);
                return result;
            } else {
                long result = fetchAmt(stoneValue * 2024, remainingBlinks - 1);
                memo.put(key, result);
                return result;
            }
        }
    }
}