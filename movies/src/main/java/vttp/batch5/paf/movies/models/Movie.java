package vttp.batch5.paf.movies.models;

import java.util.List;

public class Movie {
    
    private String imdbid;
    private String title;
    private List<String> directors;
    private String overview;
    private String tagline;
    private List<String> genres;
    private double imdbRating;
    private int imdbVotes;
    
    public String getImdbid() {
        return imdbid;
    }
    public void setImdbid(String imdbid) {
        this.imdbid = imdbid;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public List<String> getDirectors() {
        return directors;
    }
    public void setDirectors(List<String> directors) {
        this.directors = directors;
    }
    public String getOverview() {
        return overview;
    }
    public void setOverview(String overview) {
        this.overview = overview;
    }
    public String getTagline() {
        return tagline;
    }
    public void setTagline(String tagline) {
        this.tagline = tagline;
    }
    public List<String> getGenres() {
        return genres;
    }
    public void setGenres(List<String> genres) {
        this.genres = genres;
    }
    public double getImdbRating() {
        return imdbRating;
    }
    public void setImdbRating(double imdbRating) {
        this.imdbRating = imdbRating;
    }
    public int getImdbVotes() {
        return imdbVotes;
    }
    public void setImdbVotes(int imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    
    
    
}
