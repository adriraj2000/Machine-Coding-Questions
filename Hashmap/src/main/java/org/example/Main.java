package org.example;

public class Main {
    public static void main(String[] args) {
        MinHeap minHeap = new MinHeap(10);

        minHeap.insert(4);
        minHeap.insert(9);
        minHeap.insert(2);
        minHeap.insert(7);
        minHeap.insert(5);
        minHeap.insert(3);

        System.out.println("Min heap after insertions:");
        printHeap(minHeap);
    }

    private static void printHeap(MinHeap minHeap) {
        while (!minHeap.isEmpty()) {
            System.out.print(minHeap.extractMin() + " ");
        }
        System.out.println();
    }

}