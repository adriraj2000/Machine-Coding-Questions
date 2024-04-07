package org.example;

import org.example.model.Movie;
import org.example.service.NamespaceSearchEngine;
import org.example.strategy.FilterStrategy;
import org.example.strategy.YearFilter;

public class Main {
    public static void main(String[] args) {
        NamespaceSearchEngine movieSearchEngine = NamespaceSearchEngine.getInstance();
        movieSearchEngine.addDocument("Movies", new Movie("Avenger", 8.5, 2017));
        movieSearchEngine.addDocument("Movies", new Movie("Batman",8.9, 2008));
        movieSearchEngine.addDocument("Movies", new Movie("Superman", 7.8, 2013));
        FilterStrategy filterStrategy = new YearFilter(2010);
        System.out.println(movieSearchEngine.search("Movies",filterStrategy, "year", true));
    }
}