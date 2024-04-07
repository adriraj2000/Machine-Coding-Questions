package org.example.impl;

import org.example.model.TrieNode;
import org.example.tracking.TrackingSystem;

import java.util.List;

public class PendencySystem<T> implements TrackingSystem<T> {
    private TrieNode<T> root;

    public PendencySystem() {
        root = new TrieNode<>();
    }

    @Override
    public void startTracking(T id, List<String> hierarchicalTags) {
        TrieNode<T> current = root;
        for (String tag : hierarchicalTags) {
            current = current.children.computeIfAbsent(tag, k -> new TrieNode<>());
            current.entities.add(id);
        }
    }

    @Override
    public void stopTracking(T id) {
        removeEntity(root, id);
    }

    private void removeEntity(TrieNode<T> node, T id) {
        for (TrieNode<T> child : node.children.values()) {
            removeEntity(child, id);
        }
        node.entities.remove(id);
    }

    @Override
    public int getCounts(List<String> tags) {
        TrieNode<T> current = root;
        for (String tag : tags) {
            current = current.children.get(tag);
            if (current == null) {
                return 0;
            }
        }
        return current.entities.size();
    }
}
