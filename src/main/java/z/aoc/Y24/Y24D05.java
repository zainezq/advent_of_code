package z.aoc.Y24;

import z.aoc.util.Day;

import java.util.*;

public class Y24D05 extends Day {
    List<String> lines = getInputList();
    List<String> lines2 = getInputList();
    static int middleSum = 0;
    static int correctedMiddleSum = 0;

    @Override
    public Object part1() {
        // Split the input into rules and updates
        List<String> rules = new ArrayList<>();
        List<String> updates = new ArrayList<>();
        boolean emptyLineFound = false;
        for (String line : lines) {
            if (line.trim().isEmpty()) {
                emptyLineFound = true;
                continue;
            }
            if (!emptyLineFound) {
                rules.add(line);
            } else {
                updates.add(line);
            }
        }
        Map<Integer, List<Integer>> dependencyGraph = parseRules(rules);
        for (String update : updates) {
            List<Integer> pages = parseUpdate(update);
            if (isValidOrder(pages, dependencyGraph)) {
                middleSum += findMiddlePage(pages);
            }
        }
        return middleSum;
    }

    @Override
    public Object part2() {
        // Split the input into rules and updates
        List<String> rules = new ArrayList<>();
        List<String> updates = new ArrayList<>();
        boolean emptyLineFound = false;

        for (String line : lines2) {
            if (line.trim().isEmpty()) {
                emptyLineFound = true;
                continue;
            }
            if (!emptyLineFound) {
                rules.add(line);
            } else {
                updates.add(line);
            }
        }

        // Parse the rules into a dependency graph
        Map<Integer, List<Integer>> dependencyGraph = parseRules(rules);
        for (String update : updates) {
            List<Integer> pages = parseUpdate(update);
            if (!isValidOrder(pages, dependencyGraph)) {
                List<Integer> correctedOrder = reorderPages(pages, dependencyGraph);
                correctedMiddleSum += findMiddlePage(correctedOrder);
            }
        }
        return correctedMiddleSum;
    }
    // Parse the rules into a dependency graph
    private static Map<Integer, List<Integer>> parseRules(List<String> rules) {
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (String rule : rules) {
            String[] parts = rule.split("\\|");
            int from = Integer.parseInt(parts[0]);
            int to = Integer.parseInt(parts[1]);

            graph.putIfAbsent(from, new ArrayList<>());
            graph.get(from).add(to);
        }

        return graph;
    }

    // Parse an update string into a list of page numbers
    private static List<Integer> parseUpdate(String update) {
        String[] parts = update.split(",");
        List<Integer> pages = new ArrayList<>();
        for (String part : parts) {
            pages.add(Integer.parseInt(part.trim()));
        }
        return pages;
    }

    // Check if the update's order is valid
    private static boolean isValidOrder(List<Integer> pages, Map<Integer, List<Integer>> graph) {
        // Map each page to its position for quick lookup
        Map<Integer, Integer> positions = new HashMap<>();
        for (int i = 0; i < pages.size(); i++) {
            positions.put(pages.get(i), i);
        }

        // Check all dependencies
        for (Map.Entry<Integer, List<Integer>> entry : graph.entrySet()) {
            int from = entry.getKey();
            if (!positions.containsKey(from)) continue;

            for (int to : entry.getValue()) {
                if (!positions.containsKey(to)) continue;

                // from must appear before to
                if (positions.get(from) >= positions.get(to)) {
                    return false;
                }
            }
        }

        return true;
    }

    // Find the middle page of a list of pages
    private static int findMiddlePage(List<Integer> pages) {
        return pages.get(pages.size() / 2);
    }


    // Reorder the pages using topological sorting
    private static List<Integer> reorderPages(List<Integer> pages, Map<Integer, List<Integer>> graph) {
        // Create a graph containing only the pages in this update
        Map<Integer, List<Integer>> subGraph = new HashMap<>();
        for (int page : pages) {
            subGraph.put(page, new ArrayList<>());
        }

        // Add edges from the global graph that apply to this update
        for (int page : pages) {
            if (graph.containsKey(page)) {
                for (int dependent : graph.get(page)) {
                    if (pages.contains(dependent)) {
                        subGraph.get(page).add(dependent);
                    }
                }
            }
        }

        // Perform topological sort
        return topologicalSort(subGraph);
    }

    // Perform topological sort
    private static List<Integer> topologicalSort(Map<Integer, List<Integer>> graph) {
        List<Integer> sorted = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Set<Integer> visiting = new HashSet<>();

        for (int node : graph.keySet()) {
            if (!visited.contains(node)) {
                if (!dfs(node, graph, visited, visiting, sorted)) {
                    throw new RuntimeException("Cycle detected in dependency graph");
                }
            }
        }

        Collections.reverse(sorted); // Reverse the list to get the correct order
        return sorted;
    }

    private static boolean dfs(int node, Map<Integer, List<Integer>> graph, Set<Integer> visited, Set<Integer> visiting, List<Integer> sorted) {
        if (visiting.contains(node)) {
            return false; // Cycle detected
        }

        if (visited.contains(node)) {
            return true; // Already processed
        }

        visiting.add(node);
        for (int neighbor : graph.getOrDefault(node, Collections.emptyList())) {
            if (!dfs(neighbor, graph, visited, visiting, sorted)) {
                return false;
            }
        }
        visiting.remove(node);
        visited.add(node);
        sorted.add(node);

        return true;
    }

}
