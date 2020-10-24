package com.renj.struccture.linked;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * <p>
 * 创建时间：2020-10-20   13:59
 * <p>
 * 描述：双向链表
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public class DoubleLinkedList<T> {
    private Node<T> header;
    private Node<T> tail;
    private int size;

    public T addFirst(T value) {
        Node<T> newNode = new Node<>(value);
        if (header != null) {
            newNode.next = header;
            header.prev = newNode;
        }
        header = newNode;

        if (tail == null) {
            tail = header;
            tail.prev = header;
            header.next = tail;

            // 不组成循环
            // header.prev = null;
            // tail.next = null;

            // 组成循环
            header.prev = tail;
            tail.next = header;
        }
        changeSize(true);
        return value;
    }

    public T addLast(T value) {
        if (header == null || size == 0) {
            return addFirst(value);
        } else {
            final Node<T> newNode = new Node<>(value);
            final Node<T> temp = tail;
            tail = newNode;
            newNode.prev = temp;
            temp.next = newNode;

            // 不组成循环
            // header.prev = null;
            // tail.next = null;

            // 组成循环
            header.prev = tail;
            tail.next = header;

            changeSize(true);
            return value;
        }
    }

    public T add(int index, T value) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Parameter values should be between 0 and " + size);

        if (header == null || size == index) {
            return addLast(value);
        } else {
            final Node<T> newNode = new Node<>(value);
            if (index == 0) {
                newNode.next = header;
                header.prev = newNode;
                header = newNode;
            } else {
                Node<T> nodeByIndex = getNodeByIndex(index);
                newNode.prev = nodeByIndex.prev;
                newNode.next = nodeByIndex;
                nodeByIndex.prev.next = newNode;
                nodeByIndex.prev = newNode;
            }
            changeSize(true);
            return value;
        }
    }

    public T set(int index, T value) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Parameter values should be between 0 and " + (size - 1));

        final Node<T> nodeByIndex = getNodeByIndex(index);
        T oldValue = nodeByIndex.value;
        nodeByIndex.value = value;
        return oldValue;
    }

    public T removeFirst() {
        if (isEmpty()) return null;
        if (header == null) return null;

        final Node<T> temp = header;
        header = header.next;
        if (header != null) {
            // 不组成循环
            // header.prev = null;
            // tail.next = null;

            // 组成循环
            header.prev = tail;
            tail.next = header;
        }

        T rValue = temp.value;
        temp.next = null;
        temp.prev = null; // help gc

        changeSize(false);
        return rValue;
    }

    public T removeLast() {
        if (isEmpty()) return null;
        if (tail == null) return null;

        final Node<T> temp = tail;
        tail = tail.prev;
        if (tail != null) {
            // 不组成循环
            // header.prev = null;
            // tail.next = null;

            // 组成循环
            header.prev = tail;
            tail.next = header;
        }

        T rValue = temp.value;
        temp.next = null;
        temp.prev = null; // help gc

        changeSize(false);
        return rValue;
    }

    public T remove(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Parameter values should be between 0 and " + (size - 1));

        if (index == size - 1)
            return removeLast();

        Node<T> nodeByIndex = getNodeByIndex(index);
        nodeByIndex.prev.next = nodeByIndex.next;
        nodeByIndex.next.prev = nodeByIndex.prev;

        T rValue = nodeByIndex.value;
        nodeByIndex.prev = null;
        nodeByIndex.next = null;
        nodeByIndex.value = null;

        changeSize(false);
        return rValue;
    }

    public T get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Parameter values should be between 0 and " + (size - 1));

        return getNodeByIndex(index).value;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        header = null;
        tail = null;
        size = 0;
    }

    public void println() {
        System.out.println("----------------- 打印链表 ----------------- ");
        System.out.println("linked size: " + size);
        Node<T> node = header;
        for (int i = 0; i < size; i++) {
            System.out.println(node);
            node = node.next;
        }
    }

    private void changeSize(boolean isAdd) {
        if (isAdd) size++;
        else size--;

        if (size <= 0) clear();
    }

    private Node<T> getNodeByIndex(int index) {
        if (index < (size >> 1)) {
            Node<T> node = header;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;
        } else {
            Node<T> node = tail;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
            return node;
        }
    }

    private static class Node<E> {
        E value;
        Node<E> prev;
        Node<E> next;

        public Node(E value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", prev=" + (prev != null ? prev.value : "") +
                    ", next=" + (next != null ? next.value : "") +
                    '}';
        }
    }
}
