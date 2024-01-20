package com.example.demo;

import java.util.Objects;

public class AdInfo {
    private String title;
    private String url;
    private String timePublished;

    public AdInfo(String title, String url, String timePublished) {
        this.title = title;
        this.url = url;
        this.timePublished = timePublished;
    }


    public String getUrl() {
        return this.url;
    }

    // Геттеры для других полей, если они нужны

    // Переопределите метод equals, если это ещё не сделано
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdInfo adInfo = (AdInfo) o;
        return Objects.equals(url, adInfo.url);
    }


    @Override
    public int hashCode() {
        return Objects.hash(title, url, timePublished);
    }

    @Override
    public String toString() {
        return "Title: " + title + "\nURL: " + url + "\nPublished: " + timePublished + "\n";
    }

    // Геттеры и сеттеры
}
