package jobs4u.core.jobapplication.services;


import jobs4u.core.backoffice.services.FileAnalyzer;

import java.io.FileReader;
import java.io.IOException;
import java.text.BreakIterator;
import java.util.*;

public class WordCounter implements Runnable {
    private final String fileName;
    private final WordQueue words;
    public WordCounter(WordQueue words, String fileName){
        this.words = words;
        this.fileName = fileName;
    }
    @Override
    public void run(){
        String content;
        try {
            content = FileAnalyzer.getContentFromFileName(fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<String> listOfWords = getWordsFromText(content);
        for (String word : listOfWords) words.addWord(word, fileName);
    }
    private List<String> getWordsFromText(String content){
        List<String> wordView = new ArrayList<>();
        BreakIterator breakIterator = BreakIterator.getWordInstance();
        breakIterator.setText(content);
        int lastIndex = breakIterator.first();
        while (BreakIterator.DONE != lastIndex) {
            int firstIndex = lastIndex;
            lastIndex = breakIterator.next();
            if (lastIndex != BreakIterator.DONE && Character.isLetterOrDigit(content.charAt(firstIndex))) {
                wordView.add(content.substring(firstIndex, lastIndex));
            }
        }
        return wordView;
    }
}
