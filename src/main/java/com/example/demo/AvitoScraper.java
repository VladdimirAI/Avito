package com.example.demo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

////public class AvitoScraper {
//
////    public static void main(String[] args) {
////        try {
////            List<AdInfo> ads = scrapeAds("https://www.avito.ru/magnitogorsk/audio_i_video/televizory_i_proektory-ASgBAgICAUSIArgJ?s=104");
////            ads.forEach(System.out::println);
////
////            System.out.println("eeee " + ads.get(0));
////
////
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////
////    }
//
//
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.TimeUnit;
//
//public class AvitoScraper {
//
//    private static AdInfo currentAdInfo = null; // Этот объект нужно инициализировать первоначальным значением, если это необходимо
//
//    public static void main(String[] args) {
//        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
//        executorService.scheduleAtFixedRate(AvitoScraper::checkForUpdates, 0, 30, TimeUnit.SECONDS);
//    }
//
//    private static void checkForUpdates() {
//        try {
//            List<AdInfo> ads = scrapeAds("https://www.avito.ru/magnitogorsk/audio_i_video/televizory_i_proektory-ASgBAgICAUSIArgJ?s=104");
//            if (ads.isEmpty()) {
//                System.out.println("Объявления не найдены.");
//                return;
//            }
//            AdInfo firstAdInfo = ads.get(0);
//
//            if (currentAdInfo != null && currentAdInfo.equals(firstAdInfo)) {
//                System.out.println("Изменений не было.");
//            }  else {
//                System.out.println("Обнаружены изменения!");
//                System.out.println(firstAdInfo.getUrl());
//                String adDescription = scrapeAdDescription(firstAdInfo.getUrl());
//            }
//
//
//        } catch (IOException e) {
//            System.err.println("Произошла ошибка при попытке проверить обновления: " + e.getMessage());
//            e.printStackTrace();
//        }
////            AdInfo firstAdInfo = ads.get(0);
////            if (currentAdInfo != null && currentAdInfo.equals(firstAdInfo)) {
////                System.out.println("Изменений не было.");
////            } else {
////                System.out.println("Обнаружены изменения!");
////                String adDescription = scrapeAdDescription(firstAdInfo.getUrl());
////                System.out.println("Текст объявления: " + adDescription);
////                // Обновляем текущий AdInfo
////                currentAdInfo = firstAdInfo;
////            }
////        } catch (IOException e) {
////            System.err.println("Произошла ошибка при попытке проверить обновления: " + e.getMessage());
////            e.printStackTrace();
////        }
//    }
//
//    public static List<AdInfo> scrapeAds(String url) throws IOException {
//        Document doc = Jsoup.connect(url).get();
//        Elements adElements = doc.select("div[data-marker=item]");
//        List<AdInfo> ads = new ArrayList<>();
//
//        for (Element adElement : adElements) {
//            String title = adElement.select("h3[itemprop=name]").text();
//            String adUrl = "https://www.avito.ru" + adElement.select("a[itemprop=url]").attr("href");
//            String timePublished = adElement.select("p[data-marker=item-date]").text();
//
//            // Логирование времени публикации
//            System.out.println("Логирование: время публикации - " + timePublished);
//
//
//            ads.add(new AdInfo(title, adUrl, timePublished));
//        }
//
//        return ads;
//    }
//
//
//
//    public static String scrapeAdDescription(String url) throws IOException {
//
//        Document doc = Jsoup.connect(url).get();
//        // Извлекаем содержимое мета-тега description
//        String description = doc.select("meta[name=description]").attr("content");
//        return description;
//    }
//
//}


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AvitoScraper {

    private static AdInfo currentAdInfo = null; // Инициализируйте это, если нужно

    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(AvitoScraper::checkForUpdates, 0, 30, TimeUnit.SECONDS);
    }

    private static void checkForUpdates() {
        try {
            List<AdInfo> ads = scrapeAds("https://www.avito.ru/magnitogorsk/audio_i_video/televizory_i_proektory-ASgBAgICAUSIArgJ?s=104");
            if (ads.isEmpty()) {
                System.out.println("Объявления не найдены.");
                return;
            }
            AdInfo firstAdInfo = ads.get(0);

            if (currentAdInfo != null && currentAdInfo.equals(firstAdInfo)) {
                System.out.println("Изменений не было.");
            } else {
                System.out.println("Обнаружены изменения!");
                String adDescription = scrapeAdDescription(firstAdInfo.getUrl());
                System.out.println("Текст объявления: " + adDescription);
                currentAdInfo = firstAdInfo; // Обновите текущий AdInfo
            }
        } catch (IOException e) {
            System.err.println("Произошла ошибка при попытке проверить обновления: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static List<AdInfo> scrapeAds(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements adElements = doc.select("div[data-marker=item]");
        List<AdInfo> ads = new ArrayList<>();

        for (Element adElement : adElements) {
            String title = adElement.select("h3[itemprop=name]").text();
            String adUrl = "https://www.avito.ru" + adElement.select("a[itemprop=url]").attr("href");
            String timePublished = adElement.select("p[data-marker=item-date]").text();
//            System.out.println("Логирование: время публикации - " + timePublished);
            ads.add(new AdInfo(title, adUrl, timePublished));
        }
        return ads;
    }

    public static String scrapeAdDescription(String url) throws IOException {
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "*/*");
        headers.put("Accept-Encoding", "gzip, deflate, br");
        headers.put("Accept-Language", "ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7");
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64)");

        Document doc = Jsoup.connect(url).headers(headers).get();
        String description = doc.select("meta[name=description]").attr("content");
        return description;
    }
}
