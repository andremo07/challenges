package com.challenges;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class StringReduction {

    public static int minLengthAfterReduction(String s) {
        // If the string is empty or has only one character, return its length
        if (s.length() <= 1) {
            return s.length();
        }

        // Set to keep track of visited states to avoid redundant computations
        Set<String> visited = new HashSet<>();

        // Queue for BFS
        Queue<String> queue = new LinkedList<>();
        queue.offer(s);
        visited.add(s);

        while (!queue.isEmpty()) {
            String current = queue.poll();

            // Try all possible reductions
            for (int i = 0; i < current.length() - 1; i++) {
                char c1 = current.charAt(i);
                char c2 = current.charAt(i + 1);
                char reduced = reduce(c1, c2);

                if (reduced != 0) {
                    String newString = current.substring(0, i) + reduced + current.substring(i + 2);

                    // If we've reduced to a single character, we're done
                    if (newString.length() == 1) {
                        return 1;
                    }

                    // If this is a new state, add it to the queue
                    if (!visited.contains(newString)) {
                        queue.offer(newString);
                        visited.add(newString);
                    }
                }
            }
        }

        // If we can't reduce to a single character, return the minimum length found
        return visited.stream().mapToInt(String::length).min().orElse(s.length());
    }

    private static char reduce(char c1, char c2) {
        if (c1 == 'a' && c2 == 'b' || c1 == 'b' && c2 == 'a') return 'c';
        if (c1 == 'b' && c2 == 'c' || c1 == 'c' && c2 == 'b') return 'a';
        if (c1 == 'c' && c2 == 'a' || c1 == 'a' && c2 == 'c') return 'b';
        return 0; // No reduction possible
    }

    public static void main(String[] args) {
        System.out.println(minLengthAfterReduction("cab"));  // Expected output: 2
        System.out.println(minLengthAfterReduction("cccc")); // Expected output: 4
        System.out.println(minLengthAfterReduction("abcabc")); // Expected output: 2
    }
}