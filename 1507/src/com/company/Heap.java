package com.company;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Heap {
    private PriorityQueue<ValueIndexPair> heap;
    private Set<Integer> removeSet;

    public Heap() {
        heap = new PriorityQueue<>((a, b) -> (a.val - b.val));
        removeSet = new HashSet<>();
    }

    public void push(int index, int val) {
        heap.add(new ValueIndexPair(val, index));
    }

    private void lazyDelete() {
        while (heap.size() > 0 && removeSet.contains(heap.peek().index)) {
            removeSet.remove(heap.peek().index);
            heap.poll();
        }
    }

    public ValueIndexPair top() {
        lazyDelete();
        return heap.peek();
    }

    public ValueIndexPair pop() {
        lazyDelete();
        return heap.poll();
    }

    public void delete(int index) {
        removeSet.add(index);
    }

    public boolean isEmpty() {
        return heap.size() == 0;
    }

}
