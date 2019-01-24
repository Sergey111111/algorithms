package com.site.bst;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParrotTest {

    @Test
    public void whenLeftCarrotMoreThanRight() {
        Parrot kesha = new Parrot();
        kesha.setName("Kesha");
        Parrot inokemtii = new Parrot();
        inokemtii.setName("Inokentii");
        assertTrue(kesha.compareTo(inokemtii) <0);
    }

}