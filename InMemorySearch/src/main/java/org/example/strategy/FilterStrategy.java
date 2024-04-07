package org.example.strategy;

import org.example.model.Document;

import java.util.List;

public interface FilterStrategy {
    public List<Document> filter(List<Document> documentList);
}
