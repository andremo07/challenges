package com.challenges;

public class StringReverse {


    public static String reverse(String str) {
        char[] charList = str.toCharArray();
        int n = charList.length;
        StringBuilder reversedString = new StringBuilder(n);

        for (int i = n-1; i >= 0; i--) {
            reversedString.append(charList[i]);
        }

        return reversedString.toString();
    }

    public static void main(String[] args) {
        System.out.println(reverse("Racecar"));
    }

}
