package com.challenges;

import java.util.HashSet;
import java.util.Set;

public class SubPalindrome {

/*    Sub-Palindrome
    Description:
    A palindrome is a string that reads the same forward and backward, e.g. 121 or tacocat. A substring is a contiguous
    subset of characters in a string. Given a string s, how many distinct substrings of s are palindromes?

    Example:
    s = "mokkori"
    Some of its substrings are [m, o, k, r, i, mo, ok, mok, okk, kk, okko].
    There are 7 distinct palindromes: [m, o, k, r, i, kk, okko].

    Function Description:
    Complete the function palindrome in the editor below.
    palindrome has the following parameter(s):
    string s: a string
    Output:
    int: the number of distinct palindromic substrings in s
    */

    public static int palindrome(String s) {
        Set<String> palindromeList = new HashSet<>();
        int length = s.length();

        for (int i = 1; i <= length; i++) {
            Set<String> combList = getCombinations(s, i);

            // Filtering for palindromes
            for (String comb : combList) {
                if (isPalindrome(comb)) {
                    palindromeList.add(comb);
                }
            }
        }

        return palindromeList.size();
    }

    public static Set<String> getCombinations(String s, int size) {
        int length = s.length();
        Set<String> auxSet = new HashSet<>();

        for (int i = 0; i <= length - size; i++) {
            String str = s.substring(i, i + size);
            auxSet.add(str);
        }

        return auxSet;
    }

    public static boolean isPalindrome(String s) {
        StringBuilder b = new StringBuilder(s);
        return b.reverse().toString().equals(s);
    }

    public static void main(String[] args) {
        String s = "aaa";
        System.out.println(palindrome(s)); // Output: 7
    }

}
