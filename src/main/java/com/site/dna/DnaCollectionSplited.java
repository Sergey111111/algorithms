package com.site.dna;

import java.util.*;

/**
 * here we don't move  whole the lists
 * we have several different lists and crossed values as different collection
 * In this case we don't need to change other branches at all
 * It save more resources with growing of number of branches
 * <p>
 * Prototype version
 */
public class DnaCollectionSplited implements Iterable {


    private int branchesAmount;
    private int branchesPeriod;
    private int capacity;
    private HashMap<Integer, Integer> nodeFields;
    private List<Branch> branches;
    private LinkedList<Integer> crossIndexes;

    DnaCollectionSplited(int branches, int period, int capacity) {
        this.branchesAmount = branches;
        this.branchesPeriod = period;
        this.capacity = capacity;
        this.init();
    }


    private void init() {
        nodeFields = new HashMap<>();
        this.branches = new ArrayList<>();
        this.crossIndexes = new LinkedList<>();
        for (int i = 0; i < branchesAmount; i++) {
            branches.add(new Branch(this.branchesPeriod));
        }
        for (int i = 1; i <= capacity; i++) {
            crossIndexes.add(i * branchesPeriod);
        }
    }

    public DnaCollectionSplited() {
        nodeFields = new HashMap<>();
        crossIndexes = new LinkedList<>();
    }

    /**
     * Check the logic:
     * we go through outer list, put value in first Linked List and then
     * safe the last field and remove it from the list and attach as first to next element
     * taken from map
     */
    public int put(int branchNumber, int value) {
        LinkedList<Integer> copyIndexes = (LinkedList<Integer>) crossIndexes.clone();
        for (LinkedList<Integer> subList : branches.get(branchNumber).getBranch()) {
            subList.addFirst(value);
            if (subList.size() > branchesPeriod) {
                if (nodeFields.containsKey(copyIndexes.peekFirst())) {
                    value = nodeFields.get(copyIndexes.peekFirst());
                }
                nodeFields.put(copyIndexes.removeFirst(), subList.removeLast());
            } else {
                break;
            }
        }

        return value;
    }


    @Override
    public Iterator iterator() {
        LinkedList<Integer> iteratorStorage = new LinkedList<>();
        LinkedList<Integer> iteratorCrossedFields = new LinkedList<>();
        synchronized (this) {
            for (Integer crossField : nodeFields.keySet()) {
                iteratorCrossedFields.add(nodeFields.get(crossField));
            }
            for (LinkedList<Integer> subList : branches.get(0).getBranch()) {
                iteratorStorage.addAll(subList);
                iteratorStorage.add(iteratorCrossedFields.pollFirst());
            }
        }

        return new Iterator() {
            @Override
            public boolean hasNext() {
                return !iteratorStorage.isEmpty();
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return iteratorStorage.poll();
            }
        };
    }

    private static class Branch {
        ArrayList<LinkedList<Integer>> branch;

        Branch(int capacity) {
            branch = new ArrayList<>();
            for (int i = 0; i < capacity; i++) {
                branch.add(new LinkedList<Integer>());
            }
        }

        ArrayList<LinkedList<Integer>> getBranch() {
            return branch;
        }
    }
}
