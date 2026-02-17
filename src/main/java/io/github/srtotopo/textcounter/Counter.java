package io.github.srtotopo.textcounter;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Counter {
    private final int characterCount;
    private final int wordsCount;
    private final Map<String, Integer> wordsFrequency = new HashMap<>();

    public Counter(String text) {
        characterCount = text.length();

        String[] words = text.trim()
                .replaceAll("\\p{P}", "")
                .toLowerCase()
                .split("\\s+");

        wordsCount = words[0].isEmpty() ? 0 : words.length;

        for (String word : words) {
            if (!word.isEmpty()) {
                wordsFrequency.merge(word, 1, Integer::sum);
            }
        }
    }

    public int getCharacterCount() {
        return characterCount;
    }

    public int getWordsCount() {
        return wordsCount;
    }

    public Map<String, Integer> getTop3Words() {
        return wordsFrequency.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(3)
                .collect(LinkedHashMap::new, (m, e) -> m.put(e.getKey(), e.getValue()), HashMap::putAll);
    }
}
