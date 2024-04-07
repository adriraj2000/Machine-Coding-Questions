package org.example.tracking;

import java.util.List;

public interface TrackingSystem<T> {
    void startTracking(T id, List<String> hierarchicalTags);
    void stopTracking(T id);
    int getCounts(List<String> tags);
}
