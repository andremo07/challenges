package com.challenges;

import java.util.*;

public class TreeConstructor {

    public static String TreeConstructor(String[] strArr) {

        // Create a map to store the parent-child relationships
        Map<Integer, Integer> parentMap = new HashMap<>();

        // Iterate through the input array
        for (String pair : strArr) {

            // Extract the child and parent nodes from the pair
            String[] nodes = pair.substring(1, pair.length() - 1).split(",");
            int child = Integer.parseInt(nodes[0]);
            int parent = Integer.parseInt(nodes[1]);

            // Check if the child node already has a parent
            if (parentMap.containsKey(child)) {
                // If the child node has a different parent, return false
                if (parentMap.get(child) != parent) {
                    return "false";
                }
            } else {
                // If the child node doesn't have a parent, add it to the map
                parentMap.put(child, parent);
            }
        }

        // Check if there are any cycles in the tree
        Set<Integer> visited = new HashSet<>();
        for (int child : parentMap.keySet()) {
            if (!isValidTree(child, parentMap, visited)) {
                return "false";
            }
        }

        // If no cycles were found, return true
        return "true";
    }

    private static boolean isValidTree(int node, Map<Integer, Integer> parentMap, Set<Integer> visited) {

        // If the node has already been visited, there is a cycle
        if (visited.contains(node)) {
            return false;
        }

        // Mark the node as visited
        visited.add(node);

        // If the node has a parent, recursively check the parent
        if (parentMap.containsKey(node)) {
            return isValidTree(parentMap.get(node), parentMap, visited);
        }

        // If the node has no parent, it is the root and the tree is valid
        return true;
    }

    public static void main(String[] args) {

        String[] strArr = {"(1,2)", "(2,4)", "(7,2)"};
        String result = TreeConstructor(strArr);
        System.out.println(result);
    }
}
