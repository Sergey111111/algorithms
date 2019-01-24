package com.site.bst;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class BinarySearchTreeTest {
    @Test
    public void whenAddNodesThenAdded(){
        Node<Integer> node = new Node<Integer>(5);
        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>(node);
        tree.add(new Node(3));
        tree.add(new Node(7));
        tree.add(new Node(9));
    }
    @Test
    public void whenTreeIsBinaryThenItReturnsTrue() {
        Node<Integer> node = new Node<Integer>(5);
        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>(node);
        tree.add(new Node(3));
        tree.add(new Node(7));
        tree.add(new Node(9));
        tree.add(new Node(10));
        assertTrue(tree.isBinary(tree.root));
    }

    @Test
    public void whenIterateTree() {
        Node<Integer> node = new Node<Integer>(5);
        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>(node);
        tree.add(new Node(3));
        tree.add(new Node(7));
        tree.add(new Node(9));
        tree.add(new Node(10));
        Iterator<Integer> it = tree.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

}