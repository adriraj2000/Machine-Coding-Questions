package org.example.strategy;

import org.example.model.Document;
import org.example.model.Movie;

import java.util.Comparator;
import java.util.List;

public class SortingStrategy {
    public List<Document> sort(List<Document> documents, String key, boolean ascending) {
        Comparator<Document> comparator;
        switch (key) {
            case "rating":
                comparator = Comparator.comparingDouble(doc -> ((Movie) doc).getRating());
                break;
            case "year":
                comparator = Comparator.comparingInt(doc -> ((Movie) doc).getYear());
                break;
            default:
                throw new IllegalArgumentException("Invalid key for sorting: " + key);
        }

        if (!ascending) {
            comparator = comparator.reversed();
        }

        documents.sort(comparator);
        return documents;
    }
}
