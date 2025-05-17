package org.example;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class KeyValueStore {
    private Map<String,VersionedValue> store;
    private AtomicLong globalVersion;
    private ThreadLocal<Transaction> currentTransaction;

    public KeyValueStore(){
        this.store = new ConcurrentHashMap<>();
        this.globalVersion = new AtomicLong(0);
        this.currentTransaction = new ThreadLocal<>();
    }

    /*
    1. Check if active transaction
    2. If so, check transaction deleteLog and writeLog first
    3. If not found in txn, check for main data store
    4. Records the read in transaction's readLog for later conflict detection
    * */
    public String get(String key){
        if(key == null){
            throw new IllegalArgumentException("Key cannot be null");
        }
        Transaction transaction = currentTransaction.get();
        if(transaction != null){
            if(transaction.deleteLog.contains(key)) return null;
            if(transaction.writeLog.containsKey(key))return transaction.writeLog.get(key);
        }
        VersionedValue versionedValue = store.get(key);
        if(versionedValue == null)return null;
        if(transaction != null){
            transaction.readLog.put(key,versionedValue);
        }
        return versionedValue.value;
    }

    /*
    1. If active txn, adds to writeLog
    2. if not, directly update store
    * */
    public void put(String key,String value){
        if(key == null){
            throw new IllegalArgumentException("Key cannot be null");
        }
        Transaction transaction = currentTransaction.get();
        if(transaction != null){
            transaction.writeLog.put(key,value);
            transaction.deleteLog.remove(key);
        } else{
            store.put(key, new VersionedValue(value,globalVersion.incrementAndGet()));
        }
    }

    /*
    1. If in txn, add to deleteLog, remove from writeLog
    2. If not, directly remove from store
    * */
    public void delete(String key){
        if(key == null){
            throw new IllegalArgumentException("Key cannot be null");
        }
        Transaction transaction = currentTransaction.get();
        if(transaction != null){
            transaction.writeLog.remove(key);
            transaction.deleteLog.add(key);
        } else{
            store.remove(key);
        }
    }

    public void begin(){
        if (currentTransaction.get() != null) {
            throw new IllegalStateException("Transaction already in progress");
        }
        currentTransaction.set(new Transaction());
    }

    public void rollback() {
        Transaction transaction = currentTransaction.get();
        if (transaction == null) {
            throw new IllegalStateException("No active transaction");
        }
        currentTransaction.remove();
    }

    /*
    1. Checks for conflict by comparing version -> readLog & currentVersion
    2. If no conflict, apply changes from writeLog & deleteLog
    3. return true/false based on commit result
    * */
    public boolean commit(){
        Transaction transaction = currentTransaction.get();
        if (transaction == null) {
            throw new IllegalStateException("No active transaction");
        }

        for(Map.Entry<String,VersionedValue> entry : transaction.readLog.entrySet()){
            VersionedValue versionedValue = store.get(entry.getKey());
            if(versionedValue == null || versionedValue.version != entry.getValue().version){
                currentTransaction.remove();
                return false;
            }
        }

        // Apply changes
        for (String key : transaction.deleteLog) {
            store.remove(key);
        }
        for (Map.Entry<String, String> entry : transaction.writeLog.entrySet()) {
            store.put(entry.getKey(), new VersionedValue(entry.getValue(), globalVersion.incrementAndGet()));
        }

        currentTransaction.remove();
        return true;
    }
}