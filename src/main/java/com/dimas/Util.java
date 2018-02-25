package com.dimas;

import java.util.Map;

public class Util {

    public static int maxValue(Map<Integer, Integer> map) {
        Map.Entry<Integer, Integer> maxEntry = null;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
                maxEntry = entry;
            }
        }
        return maxEntry == null ? 0 : maxEntry.getKey();
    }

    public static void updateStatistics(Map<Integer, Integer> statistics, int key, int weight) {
        if (statistics.containsKey(key)) {
            int count = statistics.get(key);
            statistics.put(key, count + weight);
        } else {
            statistics.put(key, 1 * weight);
        }
    }
}
