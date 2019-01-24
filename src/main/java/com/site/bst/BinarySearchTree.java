package com.site.bst;

import java.util.Iterator;
import java.util.LinkedList;

public class BinarySearchTree<E extends Comparable> implements Iterable<E> {

    Node<E> root;
    LinkedList<Node<E>> iteratorList = new LinkedList<>();

    public BinarySearchTree(Node<E> root) {
        this.root = root;
    }

    public void add(Node node) {
        addNode(root, node);
    }

    private Node addNode(Node root, Node node) {
        if (root == null) {
            return new Node(node.e);
        }
        if (node.e.compareTo(root.e) < 0) {
            root.left = addNode(root.left, node);
        } else if (node.e.compareTo(root.e) > 0) {
            root.right = addNode(root.right, node);
        } else {
            return root;
        }
        return root;
    }

    public boolean isBinary(Node root) {
        if (root == null) {
            return true;
        }
        if ((root.left != null && root.e.compareTo(root.left.e) < 0) ||
                (root.right != null && root.e.compareTo(root.right.e) > 0)
                ) {
            return false;
        }
        return isBinary(root.left) && isBinary(root.right);
    }

    @Override
    public Iterator<E> iterator() {
        Node<E> copyRoot = root;
        while (copyRoot != null) {
            iteratorList.addLast(copyRoot);
            copyRoot = copyRoot.left;
        }
        return new Iterator<E>() {

            @Override
            public boolean hasNext() {
                return !iteratorList.isEmpty();
            }

            @Override
            public E next() {
                Node<E> result = iteratorList.pollLast();
                Node<E> key = null;
                if (result.right != null) {
                    key = result.right;
                }
                while (key != null) {
                    iteratorList.add(key);
                    key = key.left;
                }
                return result.e;
            }
        };
    }
}
