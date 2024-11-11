package com.challenges;

import java.util.HashMap;

public class MinWindowSubstring {

    public static String findMinWindow(String s, String t) {
        if (s.isEmpty() || t.isEmpty() || s.length() < t.length()) {
            return "";
        }

        HashMap<Character, Integer> charCount = new HashMap<>();
        for (char c : t.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }

        int start = 0, minStart = 0, minLen = Integer.MAX_VALUE, matched = 0;

        for (int end = 0; end < s.length(); end++) {
            char endChar = s.charAt(end);
            if (charCount.containsKey(endChar)) {
                charCount.put(endChar, charCount.get(endChar) - 1);
                if (charCount.get(endChar) >= 0) {
                    matched++;
                }
            }

            while (matched == t.length()) {
                if (end - start + 1 < minLen) {
                    minLen = end - start + 1;
                    minStart = start;
                }

                char startChar = s.charAt(start);
                if (charCount.containsKey(startChar)) {
                    charCount.put(startChar, charCount.get(startChar) + 1);
                    if (charCount.get(startChar) > 0) {
                        matched--;
                    }
                }
                start++;
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }


    public static void main(String[] args) {
        String s1 = "ahffaksfajeburne";
        String t1 = "jefab";
        String result1 = findMinWindow(s1, t1);
        System.out.println("Result 1: " + result1); // Output: aksfaje

        String s2 = "aafhhkksemckelloe";
        String t2 = "thea";
        String result2 = findMinWindow(s2, t2);
        System.out.println("Result 2: " + result2); // Output: hkksemckello
    }
}