package org.example;

public class MinHeap {
    private int[] heap;
    private int size;
    private int capacity;

    public MinHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = new int[capacity];
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }

    private int leftChild(int index) {
        return 2 * index + 1;
    }

    private int rightChild(int index) {
        return 2 * index + 2;
    }

    private void swap(int index1, int index2) {
        int temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }

    private void heapifyUp(int index) {
        int parentIndex = parent(index);
        if (index > 0 && heap[index] < heap[parentIndex]) {
            swap(index, parentIndex);
            heapifyUp(parentIndex);
        }
    }

    private void heapifyDown(int index) {
        int minIndex = index;
        int leftIndex = leftChild(index);
        int rightIndex = rightChild(index);

        if (leftIndex < size && heap[leftIndex] < heap[minIndex]) {
            minIndex = leftIndex;
        }

        if (rightIndex < size && heap[rightIndex] < heap[minIndex]) {
            minIndex = rightIndex;
        }

        if (minIndex != index) {
            swap(index, minIndex);
            heapifyDown(minIndex);
        }
    }

    public void insert(int value) {
        if (size >= capacity) {
            throw new IllegalStateException("Heap is full");
        }

        heap[size] = value;
        size++;
        heapifyUp(size - 1);
    }

    public int extractMin() {
        if (size <= 0) {
            throw new IllegalStateException("Heap is empty");
        }

        int min = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapifyDown(0);
        return min;
    }

    public int peek() {
        if (size <= 0) {
            throw new IllegalStateException("Heap is empty");
        }
        return heap[0];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    public void remove(int value) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (heap[i] == value) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new IllegalArgumentException("Value not found in the heap");
        }
        heap[index] = heap[size - 1];
        size--;
        heapifyDown(index);
    }
}
