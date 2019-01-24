package com.site.dna;

import java.util.*;

/**
 * First version of collection
 * Only two lists and hardcoded data
 * This is prototype because it works really slow
 */
public class DnaCollection{
    //todo make proper encapsulation
    LinkedList<Integer> listA = new LinkedList<>();
    LinkedList<Integer> listB = new LinkedList<>();
    HashMap<Integer, Integer> crossFields = new HashMap<>();


    public DnaCollection() {
        //todo make initialization in constructor
    }

    public void init() {
        //todo temp method to hardcode collections
        listA = (LinkedList<Integer>) List.of(1, 3, 5, 7, 8, 9, 11);
        listB = (LinkedList<Integer>) List.of(2, 4, 5, 6, 8, 9, 11);
        crossFields.put(2, 5);
        crossFields.put(4, 8);
    }

    public int putA(int value) {
        listA.addFirst(value);
        this.reDrawFields(listA);
        return listA.poll();
    }

    public int putB(int value) {
        listB.addFirst(value);
        this.reDrawFields(listB);
        return listB.poll();
    }


    public void reDrawFields(LinkedList<Integer> list) {
        Set<Integer> indexes = crossFields.keySet();
        for (Integer index : indexes) {
            crossFields.put(index, list.get(index));
        }
    }


}
