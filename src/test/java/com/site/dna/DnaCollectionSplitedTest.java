package com.site.dna;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class DnaCollectionSplitedTest {
    @Test
    public void whenUseCollectionThenWorks() {
        DnaCollectionSplited dna = new DnaCollectionSplited();
        Iterator iterator = dna.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        System.out.println();
            dna.put(777);
        Iterator iterator2 = dna.iterator();
        while (iterator2.hasNext()) {
            System.out.println(iterator2.next());
        }
    }

}