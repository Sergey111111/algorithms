package com.site.bst;

public class Node<E extends Comparable> {
    E e;
    Node<E> left;
    Node<E> right;
    public Node(E e) {
        this.e = e;
    }
}
