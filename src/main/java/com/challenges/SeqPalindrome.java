package com.challenges;

import java.util.ArrayList;
import java.util.List;

public class SeqPalindrome {

    /*
     */

    public static int countPalindromes(String s) {
        List<String> seqList = generateFixedLengthSubsequences(s, 5);
        List<String> palindromeList = filterPalindromes(seqList);
        return palindromeList.size();
    }

    private static List<String> filterPalindromes(List<String> seqList) {
        List<String> palindromeList = new ArrayList<>();
        for (String s : seqList) {
            if (isPalindrome(s)) {
                palindromeList.add(s);
            }
        }

        return palindromeList;
    }

    private static boolean isPalindrome(String s) {
        return new StringBuilder(s).reverse().toString().equals(s);
    }


    public static List<String> generateFixedLengthSubsequences(String input, int length) {
        List<String> subsequences = new ArrayList<>();
        int n = input.length();

        // There are 2^n possible subsequences for a string of length n
        int totalCombinations = 1 << n;

        for (int mask = 0; mask < totalCombinations; mask++) {
            StringBuilder currentSubsequence = new StringBuilder();

            // Use mask to decide which characters to include
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    currentSubsequence.append(input.charAt(i));
                }
            }

            // Add to result if it matches the specified length
            if (currentSubsequence.length() == length) {
                subsequences.add(currentSubsequence.toString());
            }
        }

        return subsequences;
    }

    public static void main(String[] args) {
        String s = "95664210873507549084361027559308288038020451384423";
        System.out.println(countPalindromes(s)); // Output: 7
    }

}
