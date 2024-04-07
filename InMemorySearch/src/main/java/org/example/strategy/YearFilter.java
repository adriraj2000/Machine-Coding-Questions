package org.example.strategy;

import org.example.model.Document;
import org.example.model.Movie;

import java.util.List;
import java.util.stream.Collectors;

public class YearFilter implements FilterStrategy{
    private int threshold;
    public YearFilter(int threshold){
        this.threshold = threshold;
    }
    @Override
    public List<Document> filter(List<Document> documentList) {
        return documentList.stream()
                .filter(doc -> {
                    if (doc instanceof Movie) {
                        return ((Movie) doc).getYear() >= threshold;
                    }
                    return false;
                })
                .collect(Collectors.toList());
    }
}
