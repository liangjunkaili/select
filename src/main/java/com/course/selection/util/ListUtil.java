package com.course.selection.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 并发List：Vector、CopyOnWriteArrayList
 * 在读多写少的高并发环境中，使用CopyOnWriteArrayList可以提高系统的性能
 * 在写多读少的场合，优先使用Vector
 */
public class ListUtil {
    /**
     * 利用ReentrantLock对写操作加锁，读操作不加锁
     */
    public void copyOnWriteArrayList(){
        CopyOnWriteArrayList copy = new CopyOnWriteArrayList();
        copy.add(1);
    }

    /**
     * 利用synchronized对所有操作(方法)加锁
     */
    public void vector(){
        Vector v = new Vector();
        v.add(1);
    }

    /**
     * 利用synchronized对整个list加锁
     */
    public void arrayList(){
        List list = new ArrayList();
        list = Collections.synchronizedList(list);
        list.add(1);
    }

    public static void main(String[] args) {
    }
}
