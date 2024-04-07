package org.example.service;

import org.example.model.Document;
import org.example.strategy.FilterStrategy;
import org.example.strategy.SortingStrategy;

import java.util.*;

public class NamespaceSearchEngine {
    private Map<String, Set<Document>> movieNameSpace;
    private static NamespaceSearchEngine instance;
    private SortingStrategy sortingStrategy;
    private NamespaceSearchEngine(){
        movieNameSpace = new HashMap<>();
        sortingStrategy = new SortingStrategy();
    }

    public void addDocument(String namespace, Document document){
        movieNameSpace.computeIfAbsent(namespace.toLowerCase(), k -> new HashSet<>()).add(document);
    }

    public List<Document> search(String namespace, FilterStrategy filterStrategy, String key, boolean ascending){
        List<Document> documents = movieNameSpace.getOrDefault(namespace.toLowerCase(), new HashSet<>()).stream().toList();
        List<Document> filteredDocuments = filterStrategy.filter(documents);
        return sortingStrategy.sort(filteredDocuments, key, ascending);
    }

    public static NamespaceSearchEngine getInstance(){
        if(instance == null){
            instance = new NamespaceSearchEngine();
        }
        return instance;
    }
}
