package z.aoc.Y24;

import z.aoc.util.Day;

import java.awt.*;
import java.util.List;
import java.util.*;

public class Y24D12 extends Day {

    List<String> in = getInputList();
    Map<Character, List<Point>> coords = new HashMap<>();

    static final Point[] _DIRS = {
            new Point(-1, 0), // u
            new Point(0, 1),  // r
            new Point(1, 0),  // d
            new Point(0, -1)  // l
    };

    int p2 = 0;

    @Override
    public Object part1() {
        for (int i = 0; i < in.size(); i++) {
            for (int j = 0; j < in.get(i).length(); j++) {
                coords.putIfAbsent(in.get(i).charAt(j), new ArrayList<>());
                coords.get(in.get(i).charAt(j)).add(new Point(i, j));
            }
        }
        int ret = 0;

        for (var ch : coords.keySet()) {
            Set<Point> seen = new HashSet<>();
            for (var co : coords.get(ch)) {
                if (seen.contains(co)) {
                    continue;
                }
                int area = 0;
                int fence = 0;
                ArrayDeque<Point> stack = new ArrayDeque<>();
                stack.push(co);
                List<Point> fences = new ArrayList<>();
                while (!stack.isEmpty()) {
                    var cur = stack.pop();
                    if (seen.contains(cur)) {
                        continue;
                    }
                    seen.add(cur);
                    area++;
                    for (var d : _DIRS) {
                        var nd = new Point(cur.x + d.x, cur.y + d.y);
                        if (nd.x < 0 || nd.y < 0 || nd.x >= in.size() || nd.y >= in.get(0).length()) {
                            fence++;
                            fences.add(new Point(cur.x * 3 + d.x, cur.y * 3 + d.y));
                            continue;
                        } else if (in.get(nd.x).charAt(nd.y) != ch) {
                            fence++;
                            fences.add(new Point(cur.x * 3 + d.x, cur.y * 3 + d.y));
                        } else {
                            stack.push(nd);
                        }
                    }
                }
                ret += area * fence;
                p2 += area * fetchSides(fences);
            }
        }
        return ret;
    }

    @Override
    public Object part2() {
        return p2;
    }

    int fetchSides(List<Point> fences) {
        int ret = 0;

        Map<Integer, List<Integer>> rows = new HashMap<>();
        Map<Integer, List<Integer>> cols = new HashMap<>();

        for (var ip : fences) {
            if (ip.x % 3 == 0) {
                rows.putIfAbsent(ip.y, new ArrayList<>());
                rows.get(ip.y).add(ip.x);
            } else {
                cols.putIfAbsent(ip.x, new ArrayList<>());
                cols.get(ip.x).add(ip.y);
            }
        }

        for (var xx : cols.keySet()) {
            var xl = cols.get(xx);
            Collections.sort(xl);
            ret++;
            for (int i = 1; i < xl.size(); i++) {
                if (xl.get(i) - xl.get(i - 1) != 3) {
                    ret++;
                }
            }
        }

        for (var xx : rows.keySet()) {
            var xl = rows.get(xx);
            Collections.sort(xl);
            ret++;
            for (int i = 1; i < xl.size(); i++) {
                if (xl.get(i) - xl.get(i - 1) != 3) {
                    ret++;
                }
            }
        }

        return ret;
    }


}




