package com.challenges;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class WordSplit {

    public static String wordSplit(String[] strArr) {
        String sequence = strArr[0];
        String dictionary = strArr[1];
        String[] words = dictionary.split(",");
        Set<String> wordSet = new HashSet<>(Arrays.asList(words));

        for (int i = 1; i < sequence.length(); i++) {
            String firstPart = sequence.substring(0, i);
            String secondPart = sequence.substring(i);
            if (wordSet.contains(firstPart) && wordSet.contains(secondPart)) {
                return firstPart + "," + secondPart;
            }
        }

        return "not possible";
    }

    public static void main(String[] args) {
        String[] strArr1 = {"baseball", "a,all,ball,bas,base,cat,code,d,e,quit,z"};
        String[] strArr2 = {"abcgefd", "a,ab,abc,abcg,b,c,dog,e,efd,zzzz"};

        System.out.println(wordSplit(strArr1)); // Output: hello,cat
        System.out.println(wordSplit(strArr2)); // Output: not possible
    }
}
