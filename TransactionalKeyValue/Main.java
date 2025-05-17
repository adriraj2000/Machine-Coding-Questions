package org.example;

public class Main {
    public static void main(String[] args) {
        KeyValueStore store = new KeyValueStore();

        // Basic non-transactional put/get
        store.put("x", "1");
        System.out.println("x = " + store.get("x")); // Should print 1

        // Start transaction, modify x, rollback
        store.begin();
        store.put("x", "2");
        System.out.println("[TXN] x = " + store.get("x")); // Should print 2 (in transaction)
        store.rollback();
        System.out.println("x = " + store.get("x")); // Should print 1 (rollback successful)

        // Start transaction, modify x, commit
        store.begin();
        store.put("x", "3");
        System.out.println("[TXN] x = " + store.get("x")); // Should print 3
        boolean success = store.commit();
        System.out.println("Commit success? " + success); // true
        System.out.println("x = " + store.get("x")); // Should print 3

        // Test delete in transaction
        store.begin();
        store.delete("x");
        System.out.println("[TXN] x = " + store.get("x")); // Should print null
        store.rollback();
        System.out.println("x = " + store.get("x")); // Should print 3

        // Conflict detection
        store.begin();
        String valBefore = store.get("x"); // read log: version captured
        store.put("x", "4");
        store.commit(); // commits in background

        // Final check
        System.out.println("x = " + store.get("x")); // Should print 4
    }
}