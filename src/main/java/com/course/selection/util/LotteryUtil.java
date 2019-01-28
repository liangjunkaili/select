package com.course.selection.util;

import java.io.Serializable;
import java.util.Random;

public class LotteryUtil {
    private String name;
    private Random random;
    private int[] lotteryIds;
    private String[] lotteryNames;
    private int baseRate;
    private Pair<Integer, Integer>[] lotteryRate;
    private static final int NOHIT = -1;
    private static final Pair<Integer, String> NOHIT_PAIR = new Pair<Integer, String>(-1, "未中奖");

    /**
     * @param name 奖池名称
     * @param lotteryIds 奖品列表ID
     * @param lotteryNames 奖品列表名字
     * @param baseRate 基础概率
     * @param lotteryRate 各奖品概率
     */
    public LotteryUtil(String name, int[] lotteryIds, String[] lotteryNames, int baseRate, Pair<Integer, Integer>[] lotteryRate) {
        this.name = name;
        this.random = new Random();
        this.lotteryIds = lotteryIds;
        this.lotteryNames = lotteryNames;
        this.baseRate = baseRate;
        this.lotteryRate = lotteryRate;
    }
    public Pair<Integer, String> doLottery() {
        int randomInt = random.nextInt(baseRate);
        System.out.println(randomInt);
        Pair<Integer, Integer> cur;
        int hit = NOHIT;
        for (int i = 0; i < lotteryRate.length; i++) {
            cur = lotteryRate[i];
            if (randomInt <= cur.second && randomInt>= cur.first) {
                hit = i;
                break;
            }
        }
        return hit == NOHIT ? NOHIT_PAIR : new Pair<>(lotteryIds[hit], lotteryNames[hit]);
    }

    private static Pair<Integer, Integer>[] getLotteryRates(int[] ints){
        Pair<Integer, Integer>[] liveRates = new Pair[ints.length];
        int cur = 1;
        for (int i=0;i<ints.length;i++){
            liveRates[i] = new Pair<>(cur,cur+ints[i]-1);
            cur += ints[i];
        }
        return liveRates;
    }
    public static void main(String[] args) {
        String[] prizeNames = new String[]
                {"施华洛世奇手链", "浓情巧克力礼盒", "甜蜜情侣杯", "包月VIP体验卡", "20", "50成长值"};
        int[] prizeIds = new int[]{1, 2, 3, 4, 5, 6};
//        Pair<Integer, Integer>[] liveRates = new Pair[6];
//        liveRates[0] = new Pair<>(1,1);
//        liveRates[1] = new Pair<>(2,3);
//        liveRates[2] = new Pair<>(4,5);
//        liveRates[3] = new Pair<>(6,105);
//        liveRates[4] = new Pair<>(106,4605);
//        liveRates[5] = new Pair<>(4606,10000);
        int[] liveRates = new int[]{0, 0, 10000, 0, 0, 0};
        Pair<Integer, Integer>[] liveRates0 = getLotteryRates(liveRates);
        for(Pair<Integer, Integer> p:liveRates0){
            System.out.println(p.first+"=="+p.second);
        }
        Pair<Integer, String> p = new LotteryUtil("情人节组CP活动", prizeIds, prizeNames, 10000,liveRates0).doLottery();
        System.out.println(p.first+"=="+p.second);
    }
}
final class Pair<F,S> implements Serializable{
    public F first;
    public S second;
    public Pair(){

    }
    public Pair(F f,S s){
        first = f;
        second =s;
    }

    public S getSecond() {
        return second;
    }

    public void setSecond(S second) {
        this.second = second;
    }

    public F getFirst() {
        return first;
    }

    public void setFirst(F first) {
        this.first = first;
    }
}
