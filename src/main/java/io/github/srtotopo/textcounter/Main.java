package io.github.srtotopo.textcounter;

public class Main {
    static void main() {
        String text =IO.readln("Enter a text: ");
        Counter counter = new Counter(text);

        IO.println("\nCharacter count: " + counter.getCharacterCount());
        IO.println("Words count: " + counter.getWordsCount());
        IO.println("\nTop 3 words: ");

        counter.getTop3Words().forEach((word, count) -> IO.println("- " + word + ": " + count));
    }
}
