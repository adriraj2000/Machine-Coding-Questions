package org.example.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TrieNode<T> {
    public Map<String, TrieNode<T>> children;
    public Set<T> entities;

    public TrieNode() {
        children = new HashMap<>();
        entities = new HashSet<>();
    }
}
