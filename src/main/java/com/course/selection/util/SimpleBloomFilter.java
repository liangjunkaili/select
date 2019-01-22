package com.course.selection.util;

import java.util.BitSet;

/**
 * 布隆过滤器的使用场景：
 * 如何查看一个东西是否在有大量数据的池子里面。（存在误差）
 * K个哈希函数，数组的大小M
 */
public class SimpleBloomFilter {

    private static final int DEFAULT_SIZE = 2<<24;
    private static final int[] seeds = new int[]{7,11,13,31,37,61};
    private BitSet bitSet = new BitSet(DEFAULT_SIZE);
    private SimpleHash[] func = new SimpleHash[seeds.length];

    public static void main(String[] args) {
        String value = "liangjun";
        SimpleBloomFilter simpleBloomFilter = new SimpleBloomFilter();
        System.out.println(simpleBloomFilter.contains(value));
        simpleBloomFilter.add(value);
        System.out.println(simpleBloomFilter.contains(value));
    }
    public SimpleBloomFilter(){
        for (int i=0;i<seeds.length;i++){
            func[i] = new SimpleHash(DEFAULT_SIZE,seeds[i]);
        }
    }
    public void add(String value){
        for (SimpleHash f:func){
            bitSet.set(f.hash(value),true);
        }
    }
    public boolean contains(String value){
        if (value ==null){
            return false;
        }
        boolean ret = true;
        for (SimpleHash f:func){
            ret = ret && bitSet.get(f.hash(value));
        }
        return ret;
    }
    public static class SimpleHash{
        private int cap;
        private int seed;

        public SimpleHash(int cap, int seed) {
            this.cap = cap;
            this.seed = seed;
        }
        public int hash(String value){
            int result = 0;
            int len = value.length();
            for (int i=0;i<len;i++){
                result = seed*result+value.charAt(i);
            }
            return (cap - 1) & result;
        }
    }
}
