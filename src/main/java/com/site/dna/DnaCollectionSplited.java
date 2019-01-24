package com.site.dna;

import java.util.*;

/**
 * here we don't move  whole the lists
 * we have several different lists and crossed values as different collection
 * In this case we don't need to change other branches at all
 * It save more resources with growing of number of branches
 * <p>
 * Prototype version hardcoded
 */
public class DnaCollectionSplited implements Iterable {
    private ArrayList<LinkedList<Integer>> branch;
    private HashMap<Integer, Integer> crossedFields;
    LinkedList<Integer> subA1;
    LinkedList<Integer> subA2;
    LinkedList<Integer> crossIndexes;


    //todo redraw code to make it nice
    public DnaCollectionSplited() {
        crossedFields = new HashMap<>();
        subA1 = new LinkedList<>();
        subA2 = new LinkedList<>();
        branch = new ArrayList<>();
        subA1.addAll(List.of(1, 3, 5));
        subA2.addAll(List.of(7, 9, 11));
        branch.addAll(List.of(subA1, subA2));
        crossedFields.put(subA1.size() - 1, 66);
        crossedFields.put(subA1.size() + subA2.size() - 2, 77);
        crossIndexes = new LinkedList<>();
        crossIndexes.add(subA1.size() - 1);
        crossIndexes.add(subA1.size() + subA2.size() - 2);

    }

    //todo find out if I can make it recursively
    //Check the logic:
    //we go through outer list, put value in first Linked List and then
    // safe the last field and remove it from the list and attach as first to next element
    //taken from map

    int put(int value) {
        for (LinkedList<Integer> subList: branch) {
            subList.addFirst(value);
            value = crossedFields.get(crossIndexes.peekFirst());
            crossedFields.put(crossIndexes.removeFirst(), subList.removeLast());
        }
        return value;
    }

    @Override
    public Iterator iterator() {
        LinkedList<Integer> iteratorStorage = new LinkedList<>();
        LinkedList<Integer> iteratorCrossedFields = new LinkedList<>();
        synchronized (this) {
            for (Integer crossField : crossedFields.keySet()) {
                iteratorCrossedFields.add(crossedFields.get(crossField));
            }
            for (LinkedList<Integer> subList : branch) {
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
                return iteratorStorage.poll();
            }
        };
    }
}
