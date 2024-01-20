package com.example.demo;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class PhraseFinder {

   static String filePath = "C:\\avitoSlovosochetanya.txt";

    public static boolean findPhrase(String inputString) {
        try {
            // Чтение словосочетаний из файла
            List<String> phrases = Files.readAllLines(Paths.get(filePath));

            // Преобразование входной строки для упрощения поиска (в нижний регистр и удаление лишних пробелов)
            String normalizedInput = inputString.toLowerCase().replaceAll("\\s+", " ");

            // Проверка каждого словосочетания
            for (String phrase : phrases) {
                String normalizedPhrase = phrase.toLowerCase().trim();
                if (normalizedInput.contains(normalizedPhrase)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Пример использования
    public static void main(String[] args) {
        String testString = "Телевизор samsung 43: объявление о продаже в Магнитогорске на Авито. Две горизонтальные полосы на экране, просмотру с 3х метров никак не мешают.";
         // Укажите путь к вашему файлу

        boolean result = findPhrase(testString);
        System.out.println("Найдено совпадение: " + result);
    }
}
