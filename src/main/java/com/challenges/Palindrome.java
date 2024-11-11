package com.challenges;

public class Palindrome {


    public static String isPalindrome(String str) {
        str = str.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        char[] charList = str.toCharArray();
        int n = charList.length;

        for (int i = 0; i < n / 2; i++) {
            if (charList[i] != charList[n - i - 1]) {
                return "No";
            }
        }

        return "Yes";
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome("Racecar"));
    }

}
