package com.challenges;

import java.util.Arrays;
import java.util.List;

public class SerialNumber {

    public static int sumString(String number) {
        int sum = 0;

        for (int i = 0; i < number.length(); i++) {
            // Convert the character to its numeric value and add it to the sum
            sum += Character.getNumericValue(number.charAt(i));
        }

        return sum;
    }

    public static boolean isEven(int number) {
        return number % 2 == 0;
    }

    public static boolean isValidSize(List<String> parts) {
        for(String part: parts) {
            if(part.length() != 3) {
                return false;
            }
        }

        return true;
    }

    public static String SerialNumber(String str) {
        List<String> parts = Arrays.asList(str.split("\\."));

        if (parts.size() != 3) {
            return "false";
        }

        if(!isValidSize(parts)){
            return "false";
        }

        if(!isEven(sumString(parts.get(0)))) {
            return "false";
        }

        if(isEven(sumString(parts.get(1)))) {
            return "false";
        }

        char[] digits = parts.get(2).toCharArray();
        if (digits[2] <= digits[1] || digits[2] <= digits[0]) {
            return "false";
        }

        return "true";
    }

    public static void main(String[] args) {
        String str1 = "11.124.667";
        String str2 = "114.568.112";

        System.out.println(SerialNumber(str1)); // Output: false
        System.out.println(SerialNumber(str2)); // Output: true
    }
}
