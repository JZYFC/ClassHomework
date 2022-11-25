package io.github.jzyfc.Six;

import java.util.Collection;
import java.util.function.Consumer;

public class BinarySortTree<T extends Comparable<T>> {
    private class Node {
        T data;
        Node left;
        Node right;

        public Node(T data) {
            this.data = data;
            this.left = this.right = null;
        }
    }

    private Node root;

    /**
     * Construct an empty binary sort tree.
     */
    public BinarySortTree() {
        this.root = null;
    }


    /**
     * Construct a binary sort tree use data in given collection.
     *
     * @param collection elements in this collection will be used to construct a binary sort tree
     */
    public BinarySortTree(Collection<T> collection) {
        for (T elem : collection) {
            insert(elem);
        }
    }

    /**
     * Insert the given data to binary sort tree
     *
     * @param data the data will be inserted
     */
    public void insert(T data) {
        if (this.root == null) {
            this.root = new Node(data);
            return;
        }
        Node curr = root;
        Node prev = null;
        boolean left = true;
        while (true) {
            if (curr == null) {
                if (left) {
                    prev.left = new Node(data);
                } else {
                    prev.right = new Node(data);
                }
                break;
            }
            prev = curr;
            if (data.compareTo(curr.data) <= 0) {
                curr = curr.left;
            } else {
                curr = curr.right;
                left = false;
            }
        }
    }


    /**
     * Delete given data in binary sort tree
     *
     * @param data the data to be deleted
     * @return {@code true} if delete succeed, {@code false} if delete failed
     */
    public boolean delete(T data) {
        if (root == null) return false;
        boolean found = false;
        Node curr = root;
        Node prev = null;
        boolean left = true;
        while (curr != null) {
            if (data.compareTo(curr.data) < 0) {
                prev = curr;
                curr = curr.left;
            } else if (data.compareTo(curr.data) > 0) {
                prev = curr;
                curr = curr.right;
                left = false;
            } else {
                found = true;
                break;
            }
        }
        if (!found) return false;
        if (curr.left == null && curr.right == null) {
            if (prev == null) {
                root = null;
            } else {
                if (left) {
                    prev.left = null;
                } else {
                    prev.right = null;
                }
            }
        } else if (curr.left != null && curr.right != null) {
            Node subCurr = curr.left;
            while (subCurr.right != null) {
                subCurr = subCurr.right;
            }
            T tmpData = subCurr.data;
            boolean ignored = delete(subCurr.data);
            curr.data = tmpData;
        } else {
            if (curr.left != null) {
                if (prev == null) {
                    root = curr.left;
                } else {
                    if (left) {
                        prev.left = curr.left;
                    } else {
                        prev.right = curr.left;
                    }
                }
            } else {
                if (prev == null) {
                    root = curr.right;
                } else {
                    if (left) {
                        prev.left = curr.right;
                    } else {
                        prev.right = curr.right;
                    }
                }
            }
        }

        return true;
    }

    /**
     * Print all elements in binary sort tree in pre-order.
     *
     * @see #preOrder(Consumer)
     * @see Consumer
     */
    public void preOrder() {
        preOrder(System.out::println);
    }

    /**
     * Perform given operation on all elements in binary sort tree in pre-order.
     *
     * @param operation operation to be performed on elements
     * @see Consumer
     */
    public void preOrder(Consumer<T> operation) {
        preOrder(root, operation);
    }

    private void preOrder(Node node, Consumer<T> operation) {
        if (node == null) return;
        operation.accept(node.data);
        preOrder(node.left, operation);
        preOrder(node.right, operation);
    }

    /**
     * Print all elements in binary sort tree in mid-order.
     *
     * @see #midOrder(Consumer)
     * @see Consumer
     */
    public void midOrder() {
        midOrder(System.out::println);
    }

    /**
     * Perform given operation on all elements in binary sort tree in mid-order.
     *
     * @param operation operation to be performed on elements
     * @see Consumer
     */
    public void midOrder(Consumer<T> operation) {
        midOrder(root, operation);
    }

    private void midOrder(Node node, Consumer<T> operation) {
        if (node == null) return;
        midOrder(node.left, operation);
        operation.accept(node.data);
        midOrder(node.right, operation);
    }

    /**
     * Print all elements in binary sort tree in post-order.
     *
     * @see #postOrder(Consumer)
     * @see Consumer
     */
    public void postOrder() {
        postOrder(System.out::println);
    }

    /**
     * Perform given operation on all elements in binary sort tree in post-order.
     *
     * @param operation operation to be performed on elements
     * @see Consumer
     */
    public void postOrder(Consumer<T> operation) {
        postOrder(root, operation);
    }

    private void postOrder(Node node, Consumer<T> operation) {
        if (node == null) return;
        postOrder(node.left, operation);
        postOrder(node.right, operation);
        operation.accept(node.data);
    }
}
