package leetcode_1To300;

import java.util.HashMap;

/**
 * 本代码来自 Cspiration，由 @Cspiration 提供
 * 题目来源：http://leetcode.com
 * - Cspiration 致力于在 CS 领域内帮助中国人找到工作，让更多海外国人受益
 * - 现有课程：Leetcode Java 版本视频讲解（1-900题）（上）（中）（下）三部
 * - 算法基础知识（上）（下）两部；题型技巧讲解（上）（下）两部
 * - 节省刷题时间，效率提高2-3倍，初学者轻松一天10题，入门者轻松一天20题
 * - 讲师：Edward Shi
 * - 官方网站：https://cspiration.com
 * - 版权所有，转发请注明出处
 */
public class _146_LRUCache {

    /**
     * 146. LRU Cache
     * Design and implement a data structure for Least Recently Used (LRU) cache.
     * It should support the following operations: get and put.

     get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
     put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity,
     it should invalidate the least recently used item before inserting a new item.

     Follow up:
     Could you do both operations in O(1) time complexity?

     Example:

     _146_LRUCache cache = new _146_LRUCache( 2  capacity  );

    cache.put(1, 1);
    cache.put(2, 2);
    cache.get(1);       // returns 1
    cache.put(3, 3);    // evicts key 2
    cache.get(2);       // returns -1 (not found)
    cache.put(4, 4);    // evicts key 1
    cache.get(1);       // returns -1 (not found)
    cache.get(3);       // returns 3
    cache.get(4);       // returns 4

    HashMap + Double Linked List

     插入：1，不存在 -> capacity -> 1,head = null 2,head != null
          2，存在
     取出：1，不存在
          2，存在
     => 排序

     time : O(1)
     **/

    class Node { //double linkedlist
        int key;
        int value;
        Node next;
        Node pre;
        public Node(int key, int value) { //a node can stay alone(means it didn't have next and pre pointer)
            this.key = key;
            this.value = value;
        }
    }

    private HashMap<Integer, Node> map; //use int as key and node as value, still don't understand why do we need a Hashmap?
    private int capacity;
    private Node head;
    private Node tail;

    public _146_LRUCache(int capacity) {
        map = new HashMap<>();
        this.capacity = capacity;
        head = null;
        tail = null;
    }

    public int get(int key) { //get is like peek, peek the value corresponding to key, if we use this key, then this key will be the most recently used
        Node node = map.get(key);
        if (node == null) {
            return -1;
        }
        if (node != tail) { //if node is not the tail which means node is not recent added
            if (node == head) {
                head = head.next;
            } else {
                node.pre.next = node.next;
                node.next.pre = node.pre;
            }
            tail.next = node;
            node.pre = tail;
            node.next = null;
            tail = node; //after all this, node becoming the not LRU word
        }
        return node.value;
    }

    public void put(int key, int value) { //how to put? Set or insert the value if the key
        // is not already present. When the cache reached its capacity, it should invalidate
        // the least recently used item before inserting a new item. //we add to tail if not reach the capacity
        // and we pop the head before we add a new tail
        Node node = map.get(key);
        if (node != null) { //if the node exist
            node.value = value; //update the value
            if (node != tail) { //update means "now I use" so it is Not LRU and should be delete and add to tail
                if (node == head) { //if node == head
                    head = head.next; //delete
                } else { //if node in other position other than head
                    node.pre.next = node.next; //delete
                    node.next.pre = node.pre;
                }
                tail.next = node; //add node to tail
                node.pre = tail;
                node.next = null;
                tail = node;
            }
        } else { //if the node not exit, that means we should add a new node to our cache
            Node newNode = new Node(key, value);
            if (capacity == 0) { //spacial case: if cache capacity is 0
                Node temp = head;
                head = head.next;
                map.remove(temp.key);
                capacity++;
            }
            if (head == null && tail == null) { //if head and tail all null
                head = newNode;
            } else { //else
                tail.next = newNode; //add newNode to tail.next
                newNode.pre = tail;
                newNode.next = null;
            }
            tail = newNode; //update tail pointer
            map.put(key, newNode);
            capacity--; //capacity means usable capacity
        }
    }
}

//higher level
class Node {
    int key;
    int value;
    Node next;
    Node pre;
    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }

}
class LRUCache {

    private HashMap<Integer, Node> map; //becasue we have to implement get and put method in O(1)
    private int capacity;

    private Node head;
    private Node tail;

    public LRUCache(int capacity) {
        map = new HashMap<>();
        this.capacity = capacity;
        head = null;
        tail = null;
    }

    public int get(int key) { //case1: we don't have this key, case2: we have this key, but there are some subcases, like node in head position, not in head position, why we have subcases is because there are different ways to delete them.
        Node node = map.get(key);

        if(node == null) { //if don't even have this key
            return -1;
        }

        if(node != tail) { //only when node is not tail will we have to do extra process, but if node is tail, then we just need to return its value
            if(node == head) {
                head = head.next;
            } else { //not head and not tail
                node.pre.next = node.next;
                node.next.pre = node.pre;
            }
            tail.next = node;
            node.pre = tail;
            node.next = null;
            tail = tail.next;
        }

        return node.value;
    }

    public void put(int key, int value) { //update or initial a new one
        Node node = map.get(key);//trying to get the key

        if(node != null) { //if we need to update, in this "update" situation, we don't need to think about if we out reach the capacity, because it can't be
            node.value = value; //we update the value
            if(node == head) {
                head = head.next;
            } else {
                node.pre.next = node.next;
                node.next.pre = node.pre;
            }
            tail.next = node;
            node.pre = tail;
            node.next = null;
            tail = tail.next;
        } else { //if we need to initial a new node to add
            Node newNode = new Node(key, value);
            if(capacity == 0) { //if usable capacity is 0, then we have to delete the LRU and add the new one as the Not LRU
                // and the NOT LRU here is clearly head node
                // we have to delete is both in double Linkedlist and hashMap
                Node temp = head; //record for later delete
                head = head.next; //delete head
                map.remove(temp.key); //remove the head key in map
                capacity++;//usable capacity add

            }
            //above execute when usable capacity is 0 and needs to delete before add.
            //following code uses for add the new node.

            if(head == null && tail == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                newNode.pre = tail;
                tail = tail.next;
                tail.next = null;
            }
            map.put(key, newNode);
            capacity--;
        }

    }
}
