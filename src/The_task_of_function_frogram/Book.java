package The_task_of_function_frogram;

import java.util.Scanner;

public class Book {
    public static void main(String[] args) {
        Scanner scan;
        String text;
        String[] textArray;

        int sentenceCount = 0;
        int wordsCount = 0;
        int wordsCounttmp = 0;
        int indexSentenceWord = 0;
        int lettersCount = 0;
        int lettersCounttmp = 0;
        int indexSentenceLetter = 0;

        System.out.println("Введите текст:");

        scan = new Scanner(System.in);
        text = scan.nextLine();

        textArray = text.split("[.?!]");
        sentenceCount = textArray.length;

        for(int i = 0; i < sentenceCount; i++) {
            wordsCounttmp = textArray[i].split(" ").length;

            if(wordsCounttmp > wordsCount) {
                wordsCount = wordsCounttmp;
                indexSentenceWord = i;
            }
            String[] wordsArr = textArray[i].split(" ");
            for(String word : wordsArr) {
                lettersCounttmp = lettersCounter(word);

                if(lettersCounttmp > lettersCount) {
                    lettersCount = lettersCounttmp;
                    indexSentenceLetter = i;
                }
            }
        }

        System.out.println("Количество предложений:\n" + sentenceCount + "\n" +
                "Предложение с максимальным количеством слов:\n" + textArray[indexSentenceWord] + "\n" +
                "Предложение, которое содержит слово с максимальным количеством букв:\n" + textArray[indexSentenceLetter]);

    }
    private static int lettersCounter(String word) {
        int count = 0;

        word = word.replaceAll("[“:”,-_\"';()]", "");
        count = word.length();

        return count;
    }
}
