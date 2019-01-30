package com.site.dna;

import org.junit.Test;

import java.util.Iterator;
import java.util.Random;

import static org.junit.Assert.*;

public class DnaCollectionSplitedTest {
    /**
     * This is not actual test. Just a way to watch it compile
     */
    @Test
    public void whenUseCollectionThenWorks() {
        DnaCollectionSplited dna = new DnaCollectionSplited(2,3,3);
        for (int i = 0; i < 10; i++) {
            dna.put(0, new Random().nextInt());
            dna.put(1, new Random().nextInt());
        }
        Iterator iterator = dna.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        System.out.println();
        Iterator iterator2 = dna.iterator();
        while (iterator2.hasNext()) {
            System.out.println(iterator2.next());
        }
    }
}