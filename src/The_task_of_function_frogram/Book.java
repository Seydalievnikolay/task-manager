package The_task_of_function_frogram;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.PatternSyntaxException;

import static java.lang.Integer.reverse;

public class Book {
    public static void main(String[] args) {
        String message;
        String text;
        String[]textArray;
        int wordCount = 0;
        System.out.println("Введите предложение: ");
        Scanner sc = new Scanner(System.in);
        text = sc.nextLine();
        textArray = text.split("[-.?!) (,:]");
        wordCount = textArray.length;

        {
            System.out.println(" В тексте " + wordCount + " слов");
        }
        message = sc.nextLine();
        try {
            String[] words = message.replaceAll("[-.?!)(,:]", "").split("\\s");
            Map<String, Integer> counter = new HashMap<>();
            Arrays.sort(words);
            for (String i : words) {
                if (!i.isEmpty()) {
                    Integer count = counter.get(i);

                    if (count == null) {
                        count = 0;

                    }
                    counter.put(i, ++count);
                    reverse(count);

                }
            }

            System.out.println("ТОП 10:");
            for (String j : counter.keySet()) {
                System.out.println(counter.get(j) + " - " + j);
            }
        }
        catch (PatternSyntaxException e){
            System.out.println("Ошибка регулярного выражения!");
            e.printStackTrace();
        }
    }

}
