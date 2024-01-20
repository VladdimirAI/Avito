package com.example.demo.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class eee {
    public static void main(String[] args) {
        try {
//            String s = scrapeAdDescription("https://www.avito.ru/magnitogorsk/audio_i_video/televizor_samsung_70_vse_rabotaet_4097828663");
            String s = scrapeAdDescription("https://www.avito.ru/magnitogorsk/audio_i_video/televizor_bu_lg_3527707306");
            System.out.println(s);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String scrapeAdDescription(String url) throws IOException {
        System.out.println("11");

        // Здесь вы можете вставить необходимые заголовки, которые вы нашли в браузере
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "*/*");
        headers.put("Accept-Encoding", "gzip, deflate, br");
        headers.put("Accept-Language", "ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7");
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64)");
        // ... добавьте другие заголовки по аналогии

        Document doc = Jsoup.connect(url).headers(headers).get();

        String description = doc.select("meta[name=description]").attr("content");
        return description;
    }
}
