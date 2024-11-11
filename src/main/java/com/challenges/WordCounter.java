package com.challenges;

import java.util.HashMap;
import java.util.Map;

public class WordCounter {

    //Given this String "Great companies do great things", please write a method that count the occurrences
    // of each word (case insensitive) in the String and print them out.

    static class Word {

        private String w;
        private Integer counter;

        public Word(String w, Integer counter) {
            this.w = w;
            this.counter = counter;
        }

        public void setCounter(Integer counter) {
            this.counter = counter;
        }

        public void setW(String w) {
            this.w = w;
        }

        public String getW() {
            return w;
        }

        public Integer getCounter() {
            return counter;
        }
    }


    public static void wordCounter(String word) {
        String[] words = word.split(" ");

        HashMap<String,Word> wordMap = new HashMap<>();

        for (String w : words) {
            if (!wordMap.containsKey(w.toLowerCase())) {
                wordMap.put(w.toLowerCase(), new Word(w, 1));
            } else {
                Word wordAux = wordMap.get(w.toLowerCase());
                Integer counter = wordAux.getCounter() + 1;
                wordAux.setCounter(counter);
                wordMap.put(w.toLowerCase(), wordAux);
            }
        }

        for (Map.Entry<String,Word> entry : wordMap.entrySet()) {
            System.out.println(entry.getValue().getW() + ":" + entry.getValue().getCounter());
        }

    }

    public static void main(String[] args) {
        wordCounter("Great companies do great things");
    }


}
