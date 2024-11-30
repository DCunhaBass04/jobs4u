package jobs4u.core.jobapplication.services;

import java.util.*;

public class WordQueue {
    private final Map<String,Map.Entry<List<String>,Integer>> map;
    public WordQueue(){this.map = new HashMap<>();}
    public List<Map.Entry<String, Map.Entry<List<String>, Integer>>> getSortedListOfMostUsedWords(int numberWords){
        List<Map.Entry<String, Map.Entry<List<String>, Integer>>> list = new ArrayList<>(map.entrySet());
        list.sort((o1, o2) -> o2.getValue().getValue() - o1.getValue().getValue());
        return list.subList(0, numberWords);
    }

    public synchronized void addWord(String word, String fileName){
        word = word.toLowerCase();
        if (map.containsKey(word)) {
            if(!map.get(word).getKey().contains(fileName))
                map.get(word).getKey().add(fileName);
            map.put(word, new AbstractMap.SimpleEntry<>(map.get(word).getKey(), map.get(word).getValue()+1));
        }
        else map.put(word, new AbstractMap.SimpleEntry<>(new ArrayList<>(Collections.singletonList(fileName)), 1));
    }

}
