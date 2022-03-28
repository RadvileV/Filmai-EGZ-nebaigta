package com.example.filmai.model;

public class Movie {

    private int id;
    private String title;
    private String description;
    private double rating;
    private String category;

    public Movie(){}

    public Movie(int id, String title, String description, double rating, String category) {
        this.id = id;
        this.title = title;
        this.description= description;
        this.rating = rating;
        this.category = category;
    }

    public Movie(String title, String description, double rating, String category){
        this.title = title;
        this.description= description;
        this.rating = rating;
        this.category = category;
    }

    public Movie(int id, String title, String category, String description) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public double getRating() {
        return rating;
    }

    public String getCategory() {
        return category;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                ", category='" + category + '\'' +
                '}';
    }
}


