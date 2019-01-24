package com.site.bst;

public class Parrot implements Comparable<Parrot> {

    private String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;


    public int compareTo(Parrot o) {
        Object o1 = new Object();
        return this.name.length() - o.getName().length();
    }
}
