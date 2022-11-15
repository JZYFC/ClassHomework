package io.github.jzyfc.Five;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class List<T extends Comparable<T>> implements Iterable<T> {
    private Node<T> first;
    private Node<T> last;

    /**
     * Create an empty list.
     */
    public List() {
        this.first = null;
        this.last = null;
    }


    /**
     * Construct a list containing the elements in the array.
     *
     * @param array array whose elements are to be placed into list
     */
    public List(T[] array) {
        Node<T> prev = null;
        Node<T> curr = null;
        for (T elem : array) {
            curr = new Node<>(elem);
            curr.pre = prev;
            if (prev != null) prev.next = curr;
            prev = curr;
            if (first == null) first = curr;
        }
        last = curr;
    }

    /**
     * Construct a list containing the elements in the array.
     *
     * @param iter iterator of elements that are to be placed into list
     */
    public List(Iterable<T> iter) {
        Node<T> prev = null;
        Node<T> curr = null;
        for (T elem : iter) {
            curr = new Node<>(elem);
            curr.pre = prev;
            if (prev != null) prev.next = curr;
            prev = curr;
            if (first == null) first = curr;
        }
        last = curr;
    }

    /**
     * Insert an element into the list at the given position.
     *
     * @param elem  element to insert into
     * @param index index at which the specified element will be inserted
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public void insert(T elem, int index) throws IndexOutOfBoundsException {
        int currPos = 0;
        Node<T> curr = this.first;
        Node<T> prev = null;
        while (curr != null && currPos != index) {
            prev = curr;
            curr = curr.next;
            currPos += 1;
        }
        if (prev != null) {
            Node<T> n = new Node<>(elem);
            prev.next = n;
            n.pre = prev;
            n.next = curr;
            if (curr != null) curr.pre = n;
        } else {
            throw new IndexOutOfBoundsException("%d超出链表长度".formatted(index));
        }
    }

    /**
     * Delete an element at the given position.
     *
     * @param index index at which the specified element will be deleted
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public void delete(int index) throws IndexOutOfBoundsException {
        int currPos = 0;
        Node<T> curr = this.first;
        Node<T> prev = null;
        while (curr != null && currPos != index) {
            prev = curr;
            curr = curr.next;
            currPos += 1;
        }
        if (curr != null) {
            if (prev == null) {
                curr.next.pre = null;
                this.first = curr.next;
            } else {
                prev.next = curr.next;
                if (curr.next != null) {
                    curr.next.pre = prev;
                } else {
                    this.last = prev;
                }
            }
        } else {
            throw new IndexOutOfBoundsException("%d超出链表长度".formatted(index));
        }
    }

    /**
     * @param elem element to append to the list.
     */
    public void append(T elem) {
        Node<T> n = new Node<>(elem);
        n.pre = this.last;
        if (this.first == null) {
            this.first = n;
        } else {
            this.last.next = n;
        }
        this.last = n;
    }

    /**
     * Find the first occurrence of the given target
     *
     * @param target target element to find
     * @return index of the found element, if not, return -1;
     */
    public int findFirst(T target) {
        int currIndex = 0;
        Node<T> curr = this.first;
        while (curr != null) {
            if (curr.data.equals(target)) {
                return currIndex;
            }
            curr = curr.next;
            currIndex += 1;
        }
        return -1;
    }

    /**
     * Find the last occurrence of the given target
     *
     * @param target target element to find
     * @return index of the found element, if not, return -1;
     */
    public int findLast(T target) {
        int currIndex = 0;
        int ret = -1;
        Node<T> curr = this.first;
        while (curr != null) {
            if (curr.data.equals(target)) {
                ret = currIndex;
            }
            curr = curr.next;
            currIndex += 1;
        }
        return ret;
    }

    /**
     * Concatenate the given list to the end of this list.
     *
     * @param otherList the given list to be concatenated
     */
    public void concat(List<T> otherList) {
        this.last.next = otherList.first;
        otherList.first.pre = this.last;
    }

    /**
     * Sort the elements in this list using <code>T</code>'s implementation of {@link Comparable#compareTo(Object)}.
     * <p>The sort is stable</p>
     */
    public void sort() {
        sort(T::compareTo);
    }

    /**
     * Sort the elements in this list using given {@link Comparator<T>}.
     * <p>The sort is stable</p>
     *
     * @param comparator the comparator to use
     * @see Comparator
     */
    public void sort(Comparator<T> comparator) {
        Node<T> curr = this.first;
        this.first = null;
        this.last = null;

        while (curr != null) {
            Node<T> next = curr.next;
            curr.next = null;
            curr.pre = null;
            if (this.first == null) {
                this.first = curr;
            } else {
                Node<T> prev = null;
                Node<T> sorted = this.first;
                while (sorted != null && comparator.compare(sorted.data, curr.data) <= 0) {
                    prev = sorted;
                    sorted = sorted.next;
                }
                if (prev == null) {
                    curr.next = this.first;
                    this.first.pre = curr;
                    this.first = curr;
                } else {
                    prev.next.pre = curr;
                    curr.next = prev.next;
                    curr.pre = prev;
                    prev.next = curr;
                }
            }
            curr = next;
        }
    }

    public void join(List<T> otherList) {
        Node<T> index1 = this.first;
        Node<T> index2 = otherList.first;
        Node<T> prev = null;

        while (index1 != null && index2 != null) {
            if (index1.data.compareTo(index2.data) > 0) {
                Node<T> next2 = index2.next;
                if (prev != null) {
                    Node<T> next1 = prev.next;
                    index2.next = next1;
                    if (next1 != null) next1.pre = index2;
                    prev.next = index2;
                    index2.pre = prev;

                } else {
                    index2.next = this.first;
                    this.first = index2;
                    index2.pre = null;
                }
                index2 = next2;
                otherList.first = next2;
            }
            prev = index1;
            index1 = index1.next;
        }

        if (index2 != null) {
            if (prev != null) {
                prev.next = index2;
                while (index2.next != null) {
                    index2 = index2.next;
                }
                this.last = index2;
            } else {
                this.first = otherList.first;
                this.last = otherList.last;
            }
        }
        otherList.first = null;
        otherList.last = null;
    }

    /**
     * Removes all the elements from this list.
     * The list will be empty.
     */
    public void clear() {
        Node<T> curr = this.first;
        // for better gc
        while (curr != null) {
            Node<T> next = curr.next;
            curr.data = null;
            curr.pre = null;
            curr.next = null;
            curr = next;
        }

        this.first = this.last = null;
    }

    /**
     * Copy this list and return the copy.
     *
     * @return a new list contains copied elements from this list
     * @throws DeepCopyNotImplemented List not supports the deep copy operation of the type of contained element
     * @deprecated You should not use this method to generate a new list. Java 's {@link Object#clone()} is protected
     * and deep copy of a specified object is not guaranteed. This method only support {@link Integer} "deep copy".
     * (In fact {@link Integer} is immutable and deep copy is meaningless).
     */
    @SuppressWarnings({"unchecked", "UnnecessaryUnboxing"})
    @Deprecated
    public List<T> deepCopy() throws DeepCopyNotImplemented {
        if (this.first != null) {
            if (this.first.data.getClass().equals(Integer.class)) {
                List<T> list = new List<>();
                for (T elem : this) {
                    list.append((T) Integer.valueOf(((Integer) elem).intValue()));
                }
                return list;
            } else {
                throw new DeepCopyNotImplemented("%s深复制未实现".formatted(this.first.data.getClass()));
            }
        } else {
            return new List<T>();
        }
    }

    @Override
    public String toString() {
        if (this.first == this.last && this.first == null) return "Empty list";
        StringBuilder sb = new StringBuilder();
        Node<T> curr = this.first;
        boolean isFirst = true;
        while (curr != null) {
            if (!isFirst) sb.append(" -> ");
            sb.append(curr.data.toString());
            curr = curr.next;
            isFirst = false;
        }
        return sb.toString();
    }

    static class Node<E> {

        E data;

        public Node(E data) {
            this.data = data;
            this.next = null;
            this.pre = null;
        }

        Node<E> next;

        Node<E> pre;

    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new Itr(first);
    }

    class Itr implements Iterator<T> {

        Node<T> curr;

        public Itr(Node<T> curr) {
            this.curr = curr;
        }

        @Override
        public boolean hasNext() {
            return curr != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("已到达链表尾");
            }
            T data = curr.data;
            curr = curr.next;
            return data;
        }
    }

    static class DeepCopyNotImplemented extends Exception {
        public DeepCopyNotImplemented(String msg) {
            super(msg);
        }
    }
}







