package com.renj.struccture.linked;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * <p>
 * 创建时间：2020-10-20   10:29
 * <p>
 * 描述：链表测试
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public class LikedListTest {
    public static void main(String[] args) {
        System.out.println("【start】===========================  单链表 ================================【start】");
        singleLinkedListTest();
        System.out.println("【end】===========================  单链表 ================================【end】");

        System.out.println("\n\n\n【start】=========================  双链表 ==================================【start】");
        doubleLinkedListTest();
        System.out.println("【end】=========================  双链表 ==================================【end】");
    }

    private static void singleLinkedListTest() {
        SingleLinkedList<String> singleLinkedList = new SingleLinkedList<>();

        System.out.println("\n================= 增加数据 =================");
        System.out.println(singleLinkedList.addLast("1"));
        System.out.println(singleLinkedList.addLast("2"));
        System.out.println(singleLinkedList.addLast("3"));
        System.out.println(singleLinkedList.addLast("4"));
        System.out.println(singleLinkedList.add(0, "c"));
        System.out.println(singleLinkedList.addFirst("a"));
        System.out.println(singleLinkedList.add(2, "d"));
        System.out.println(singleLinkedList.add(7, "e"));
        System.out.println("数据添加完成");

        System.out.println("\n================= 查询和遍历 =================");
        System.out.println("index 3 value: " + singleLinkedList.get(3));
        singleLinkedList.println();

        System.out.println("\n================= 移除数据 =================");
        System.out.println(singleLinkedList.removeFirst());
        System.out.println(singleLinkedList.removeLast());
        System.out.println(singleLinkedList.remove(3));
        System.out.println(singleLinkedList.remove(singleLinkedList.size() - 1));
        singleLinkedList.println();

        System.out.println("\n================= 修改数据 =================");
        System.out.println(singleLinkedList.set(2, "AAA"));
        singleLinkedList.println();

        System.out.println("\n================= 是否有环 =================");
        System.out.println(singleLinkedList.hasCircle());

        System.out.println("\n================= 迭代反转 =================");
        singleLinkedList.reverseLinkedListIteration();
        singleLinkedList.println();

        System.out.println("\n================= 递归反转 =================");
        singleLinkedList.reverseLinkedListRecursive();
        singleLinkedList.println();

        System.out.println("\n================= 头插入反转法 =================");
        singleLinkedList.reverseLinkedListHeaderInsert();
        singleLinkedList.println();

        System.out.println("\n================= 就地逆置反转法 =================");
        singleLinkedList.reverseLinkedListLocalReverse();
        singleLinkedList.println();
    }

    private static void doubleLinkedListTest() {
        DoubleLinkedList<String> doubleLinkedList = new DoubleLinkedList<>();

        System.out.println("\n================= 增加数据 =================");
        System.out.println(doubleLinkedList.addLast("1"));
        System.out.println(doubleLinkedList.addLast("2"));
        System.out.println(doubleLinkedList.addLast("3"));
        System.out.println(doubleLinkedList.addLast("4"));
        System.out.println(doubleLinkedList.add(0, "c"));
        System.out.println(doubleLinkedList.addFirst("a"));
        System.out.println(doubleLinkedList.add(2, "d"));
        System.out.println(doubleLinkedList.add(7, "e"));
        System.out.println("数据添加完成");

        System.out.println("\n================= 查询和遍历 =================");
        System.out.println("index 3 value: " + doubleLinkedList.get(3));
        doubleLinkedList.println();

        System.out.println("\n================= 移除数据 =================");
        System.out.println(doubleLinkedList.removeFirst());
        System.out.println(doubleLinkedList.removeLast());
        System.out.println(doubleLinkedList.remove(3));
        System.out.println(doubleLinkedList.remove(doubleLinkedList.size() - 1));
        doubleLinkedList.println();

        System.out.println("\n================= 修改数据 =================");
        System.out.println(doubleLinkedList.set(2, "AAA"));
        doubleLinkedList.println();
    }
}
