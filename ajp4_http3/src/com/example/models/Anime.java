package com.example.models;

public class Anime {
    private int  mal_id;   //poenta je da izvucemo podatke koje nam trebaju iz one tabele
    private String url;
    private String title_English;
    private String title_Japanese;
    private double score;

    public Anime(){}

    public int getMal_id() {
        return mal_id;
    }

    public void setMal_id(int mal_id) {
        this.mal_id = mal_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle_English() {
        return title_English;
    }

    public void setTitle_English(String title_English) {
        this.title_English = title_English;
    }

    public String getTitle_Japanese() {
        return title_Japanese;
    }

    public void setTitle_Japanese(String title_Japanese) {
        this.title_Japanese = title_Japanese;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Anime{" +
                "mal_id=" + mal_id +
                ", url='" + url + '\'' +
                ", title_English='" + title_English + '\'' +
                ", title_Japanese='" + title_Japanese + '\'' +
                ", score=" + score +
                '}';
    }
}
