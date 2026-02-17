package io.github.srtotopo.textcounter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Text Counter - Character and Word Analysis Tests")
class CounterTest {

    private Counter counter;

    @Test
    @DisplayName("Count all characters including spaces in text")
    void testCharacterCountWithSpaces() {
        counter = new Counter("Hello World");
        assertEquals(11, counter.getCharacterCount());
    }

    @Test
    @DisplayName("Return zero when counting characters in empty text")
    void testCharacterCountEmpty() {
        counter = new Counter("");
        assertEquals(0, counter.getCharacterCount());
    }

    @Test
    @DisplayName("Include punctuation marks in character count")
    void testCharacterCountWithPunctuation() {
        counter = new Counter("Hello, World!");
        assertEquals(13, counter.getCharacterCount());
    }

    @Test
    @DisplayName("Count multiple words in standard text")
    void testWordCountBasic() {
        counter = new Counter("Hello World Java");
        assertEquals(3, counter.getWordsCount());
    }

    @Test
    @DisplayName("Count words correctly when text contains punctuation")
    void testWordCountWithPunctuation() {
        counter = new Counter("Hello, World! Java.");
        assertEquals(3, counter.getWordsCount());
    }

    @Test
    @DisplayName("Handle single word input correctly")
    void testWordCountSingleWord() {
        counter = new Counter("Hello");
        assertEquals(1, counter.getWordsCount());
    }

    @Test
    @DisplayName("Return zero word count for whitespace-only input")
    void testWordCountOnlyWhitespace() {
        counter = new Counter("   ");
        assertEquals(0, counter.getWordsCount());
    }

    @Test
    @DisplayName("Identify top 3 most frequent words in correct order")
    void testTop3WordsByFrequency() {
        counter = new Counter("apple apple apple banana banana cherry");
        Map<String, Integer> top3 = counter.getTop3Words();

        assertEquals(3, top3.size());
        assertTrue(top3.containsKey("apple"));
        assertTrue(top3.containsKey("banana"));
        assertTrue(top3.containsKey("cherry"));
        assertEquals(3, top3.get("apple"));
        assertEquals(2, top3.get("banana"));
        assertEquals(1, top3.get("cherry"));
    }

    @Test
    @DisplayName("Return fewer than 3 results when text has less unique words")
    void testTop3WordsLessThanThree() {
        counter = new Counter("apple banana");
        Map<String, Integer> top3 = counter.getTop3Words();

        assertEquals(2, top3.size());
        assertTrue(top3.containsKey("apple"));
        assertTrue(top3.containsKey("banana"));
    }

    @Test
    @DisplayName("Return single word result when only one unique word exists")
    void testTop3WordsSingleUnique() {
        counter = new Counter("hello hello hello");
        Map<String, Integer> top3 = counter.getTop3Words();

        assertEquals(1, top3.size());
        assertTrue(top3.containsKey("hello"));
        assertEquals(3, top3.get("hello"));
    }

    @Test
    @DisplayName("Handle case-insensitive word matching with punctuation")
    void testTop3WordsWithPunctuation() {
        counter = new Counter("Hello, hello! HELLO. world");
        Map<String, Integer> top3 = counter.getTop3Words();

        assertTrue(top3.containsKey("hello"));
        assertTrue(top3.containsKey("world"));
    }

    @Test
    @DisplayName("Maintain descending order of word frequencies in results")
    void testTop3WordsDescendingOrder() {
        counter = new Counter("zebra apple zebra banana apple zebra");
        Map<String, Integer> top3 = counter.getTop3Words();

        assertEquals(3, top3.size());
        int previousCount = Integer.MAX_VALUE;
        for (Integer count : top3.values()) {
            assertTrue(count <= previousCount, "Word frequencies must be in descending order");
            previousCount = count;
        }
    }
}