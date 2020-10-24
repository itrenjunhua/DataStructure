package com.renj.struccture.linked;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * <p>
 * 创建时间：2020-10-20   09:55
 * <p>
 * 描述：单链表实现
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public class SingleLinkedList<T> {
    private Node<T> header;
    private Node<T> tail;
    private int size;

    public T addFirst(T value) {
        final Node<T> newNode = new Node<>(value);
        if (header != null) {
            newNode.next = header;
        }
        header = newNode;
        if (tail == null) {
            tail = header;
            header.next = tail;
            // tail.next = null; // 不组成循环
            tail.next = header; // 组成循环
        }
        changeSize(true);
        return value;
    }

    public T addLast(T value) {
        if (header == null) {
            return addFirst(value);
        } else {
            final Node<T> newNode = new Node<>(value);
            final Node<T> temp = tail;
            temp.next = newNode;
            tail = newNode;

            // tail.next = null; // 不组成循环
            tail.next = header; // 组成循环
            changeSize(true);
            return value;
        }
    }

    public T add(int index, T value) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Parameter values should be between 0 and " + size);

        if (header == null || index == size) {
            return addLast(value);
        } else {
            final Node<T> newNode = new Node<>(value);
            if (index == 0) {
                newNode.next = header;
                header = newNode;
            } else {
                final Node<T> nodeByIndex = getNodeByIndex(index - 1);
                newNode.next = nodeByIndex.next;
                nodeByIndex.next = newNode;
            }
            changeSize(true);
            return value;
        }
    }

    public T removeFirst() {
        if (header == null) return null;

        final T value = header.value;
        header = header.next;
        // tail.next = null; // 不组成循环
        tail.next = header; // 组成循环
        changeSize(false);
        return value;
    }

    public T removeLast() {
        if (size == 0) return null;
        // 移除尾
        if (size > 1) {
            Node<T> nodeByIndex = getNodeByIndex(size - 2);
            tail = nodeByIndex;
            // tail.next = null; // 不组成循环
            tail.next = header; // 组成循环

            Node<T> removeNode = nodeByIndex.next;
            changeSize(false);
            return removeNode.value;
        } else {
            T value = header.value;
            clear();
            return value;
        }
    }

    public T remove(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Parameter values should be between 0 and " + (size - 1));

        if (size == 0) {
            clear();
            return null;
        } else {
            Node<T> removeNode = getNodeByIndex(index);
            Node<T> removePre = getNodeByIndex(index - 1);
            removePre.next = removeNode.next;
            T value = removeNode.value;
            changeSize(false);
            return value;
        }
    }

    public T set(int index, T value) {
        Node<T> nodeByIndex = getNodeByIndex(index);
        T oldValue = nodeByIndex.value;
        nodeByIndex.value = value;
        return oldValue;
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

    private Node<T> getNodeByIndex(int index) {
        Node<T> node = header;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    private void changeSize(boolean isAdd) {
        if (isAdd) size++;
        else size--;

        if (size == 0) clear();
    }


    // 判断是否有环
    public boolean hasCircle() {
        if (header == null) return false;
        if (tail == null) return false;
        Node<T> fast = header;
        Node<T> slow = header;

        do {
            if (fast == null) return false;
            fast = fast.next;
            if (fast == null) return false;
            fast = fast.next;
            slow = slow.next;
        } while (fast != slow);
        return true;
    }

    // 迭代反转法
    public void reverseLinkedListIteration() {
        if (header == null) return;
        if (header.next == null) return;
        if (tail == null) return;

        final Node<T> saveHeader = header;

        Node<T> beg = null;
        Node<T> mid = header;
        Node<T> end = header.next;

        while (true) {
            mid.next = beg; // 修改mid节点指向
            // end == null 表示非循环链表结束  end == saveHeader 表示循环链表结束
            if (end == null || end == saveHeader) break;
            // 整体移动
            beg = mid;
            mid = end;
            end = end.next;
        }
        header = mid;
        tail = saveHeader;
        // tail.next = null; // 不是循环链表
        tail.next = header; // 是循环链表
    }

    // 递归反转法
    public void reverseLinkedListRecursive() {
        final Node<T> saveHeader = header;
        header = reverseNode(saveHeader, header, true);
        tail = saveHeader;
        // tail.next = null; // 不是循环链表
        tail.next = header; // 是循环链表
    }

    // 递归节点
    private Node<T> reverseNode(Node<T> oldHeader, Node<T> header, boolean firstCall) {
        // 链表递归出口
        if (header == null || header.next == null) return header;

        // header.next == oldHeader 表示循环链表递归出口 增加 firstCall 是防止第一次执行就直接返回了
        if (!firstCall && header.next == oldHeader) {
            return header;
        }

        // 一直递归，找到链表中最后一个节点
        Node<T> newHeader = reverseNode(oldHeader, header.next, false);
        // 每退出一层，都需要改变 head->next 节点指针域的指向，同时令 head 所指节点的指针域为 NULL。
        header.next.next = header;
        header.next = null;
        // 每一层递归结束，都要将新的头指针返回给上一层。由此，即可保证整个递归过程中，能够一直找得到新链表的表头。
        return newHeader;
    }

    // 头插入反转法
    public void reverseLinkedListHeaderInsert() {
        if (header == null || header.next == null) return;

        final Node<T> saveHeader = header;
        Node<T> newHeader = null;
        Node<T> temp = null;

        while (true) {
            temp = header;
            header = header.next;
            temp.next = newHeader;
            newHeader = temp;
            // 到达结尾  header == saveHeader 表示循环链表达到结尾
            if (header == null || header == saveHeader) break;
        }
        header = newHeader;
        tail = saveHeader;
        // tail.next = null; // 不是循环链表
        tail.next = header; // 是循环链表
    }

    // 就地逆置反转法
    public void reverseLinkedListLocalReverse() {
        if (header == null || header.next == null) return;

        tail.next = null; // 先断开循环
        Node<T> beg = header;
        Node<T> end = header.next;

        while (true) {
            // 移动节点
            beg.next = end.next;
            end.next = header;
            // 修改指针
            header = end;
            end = beg.next;

            if (end == null) break;
        }
        // 组成循环
        tail = beg;
        tail.next = header;
    }

    private static class Node<E> {
        E value;
        Node<E> next;

        public Node(E value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", next=" + (next != null ? next.value : "") +
                    '}';
        }
    }
}
