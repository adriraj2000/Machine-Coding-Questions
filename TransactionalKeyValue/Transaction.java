package org.example;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Transaction {
    Map<String, VersionedValue> readLog;
    Map<String, String> writeLog;
    Set<String> deleteLog;

    public Transaction() {
        this.readLog = new HashMap<>();
        this.writeLog = new HashMap<>();
        this.deleteLog = new HashSet<>();
    }
}
