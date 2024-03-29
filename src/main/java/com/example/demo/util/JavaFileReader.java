package com.example.demo.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;


public class JavaFileReader {
    public static void main(String[] args) {
        String startFolder = "C:\\MYTRANSLATOR\\AVITO BOT\\demo"; // Укажите путь к начальной папке

        try {
            StringBuilder contentBuilder = new StringBuilder();
            Files.walk(Paths.get(startFolder))
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".java"))
                    .forEach(filePath -> {
                        try {
                            String fileContent = new String(Files.readAllBytes(filePath));
                            contentBuilder.append(fileContent).append("\n");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

            String allJavaCode = contentBuilder.toString();

            // Теперь можно записать содержимое в текстовый документ
            try (PrintWriter writer = new PrintWriter("demoAvitoApp.txt")) {
                writer.write(allJavaCode);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            System.out.println("Содержимое Java файлов записано в текстовый документ.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}