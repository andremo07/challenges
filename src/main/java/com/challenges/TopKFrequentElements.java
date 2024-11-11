package com.challenges;

import java.util.HashMap;
import java.util.Map;


public class TopKFrequentElements {


    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> counterMap = new HashMap<>();

        for (int i : nums) {
            if (!counterMap.containsKey(i)) {
                counterMap.put(i, 1);
            } else {
                counterMap.put(i, counterMap.get(i) + 1);
            }
        }

        int[] topK = new int[k];

        for (int i = 0; i < k; i++) {
            Map.Entry<Integer, Integer> maxValueEntry = getMax(counterMap);
            if(maxValueEntry != null){
                Integer key = maxValueEntry.getKey();
                topK[i] = key;
                counterMap.remove(key);
            }
        }

        return topK;
    }

    private static Map.Entry<Integer, Integer> getMax(Map<Integer, Integer> counterMap) {
        int maxValue = 0;
        Map.Entry<Integer, Integer> maxValueEntry = null;

        for (Map.Entry<Integer, Integer> entry : counterMap.entrySet()) {
            int value = entry.getValue();
            if (maxValue < value) {
                maxValue = value;
                maxValueEntry = entry;
            }
        }

        return maxValueEntry;
    }

    public static void main(String[] args) {
        int[] nums = {-11, 1, 1, 2, 2, 3};
        int[] topK = topKFrequent(nums, 2);

        for (int i : topK) {
            System.out.println(i);
        }
    }

}
