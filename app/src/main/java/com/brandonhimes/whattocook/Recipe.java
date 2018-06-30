package com.brandonhimes.whattocook;

/**
 * Created by brandon on 6/30/18.
 */

class Recipe {
    String publisher;
    Double social_rank;
    String f2f_url;
    String publisher_url;
    String title;
    String source_url;
    Integer page;

    public Recipe(String publisher, Double social_rank, String f2f_url, String publisher_url, String title, String source_url, Integer page) {
        this.publisher = publisher;
        this.social_rank = social_rank;
        this.f2f_url = f2f_url;
        this.publisher_url = publisher_url;
        this.title = title;
        this.source_url = source_url;
        this.page = page;
    }

    public String getPublisher() {
        return publisher;
    }

    public Double getSocial_rank() {
        return social_rank;
    }

    public String getF2f_url() {
        return f2f_url;
    }

    public String getPublisher_url() {
        return publisher_url;
    }

    public String getTitle() {
        return title;
    }

    public String getSource_url() {
        return source_url;
    }

    public Integer getPage() {
        return page;
    }
}
