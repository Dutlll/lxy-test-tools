package com.test.www.concurrent;

import javafx.util.Pair;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CompletableStudy {


    public void oneSimpleForDebug(){
        CompletableFuture<Void> oneExample = CompletableFuture.runAsync(()->{
            System.out.println("step one executed...");
        });
        oneExample.<String>thenApply((it)->{
            System.out.println("cur params:"+it);
            System.out.println("executed...");
            return "thenApply return...";
        });
    }

    public static void main(String[] args) {
        new CompletableStudy().kSum(new int[]{1,-2,3,4,-10,12},16);
    }
    public long kSum(int[] nums, int k) {
        long mx = 0;
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            if (nums[i] > 0) {
                mx += nums[i];
            } else {
                nums[i] *= -1;
            }
        }
        Arrays.sort(nums);
        PriorityQueue<Pair<Long, Integer>> pq
                = new PriorityQueue<>(Comparator.comparing(Pair::getKey));
        pq.offer(new Pair<>(0L, 0));
        while (--k > 0) {
            Pair<Long,Integer> p = pq.poll();
            long s = p.getKey();
            int i = p.getValue();
            if (i < n) {
                pq.offer(new Pair<>(s + nums[i], i + 1));
                if (i > 0) {
                    pq.offer(new Pair<>(s + nums[i] - nums[i - 1], i + 1));
                }
            }
        }
        return mx - pq.peek().getKey();
    }

    public void demo2(){
        CompletableFuture<Void> oneExample = CompletableFuture.runAsync(()->{
            System.out.println("executeOne"+Thread.currentThread());
            try {
                TimeUnit.SECONDS.sleep(1);
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println("executeOne end...");
        });

        oneExample.<String>thenApplyAsync((it)->{
            System.out.println("executeTwo start "+Thread.currentThread());
            try{
                TimeUnit.SECONDS.sleep(1);
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println("executeTwo end...");
            return "execute tow result";
        });

        oneExample.<String>thenApplyAsync((it)->{
            System.out.println("executeThree start " + Thread.currentThread());
            try{
                sleep(1);
            }catch (Exception e){
                System.out.println("executeThree error...");
            }
            System.out.println("executeThree end...");
            return "executeThree result";
        });
        sleep(100);
    }

    public static void sleep(int second){
        try{
            TimeUnit.SECONDS.sleep(second);
        }catch (Exception e){
            System.out.println("error of sleep function...");
        }
    }


    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        int len = nums.length -1;
        for (int i = 0;i < nums.length - 2;i++){
            if (i > 0 && nums[i] == nums[i - 1]){
                i ++;
            }
            int curSum = - nums[i];
            int left = i + 1;
            int right = len;
            while (left < right){
                if (nums[left] + nums[right] > curSum){
                    right--;
                }else if (nums[left] + nums[right] < curSum){
                    left++;
                }else {
                    List<Integer> tmpResult = new ArrayList<>();
                    tmpResult.add(nums[i]);
                    tmpResult.add(nums[left]);
                    tmpResult.add(nums[right]);
                    ans.add(tmpResult);
                    left ++;
                    while (left < right && nums[left] == nums[left - 1]){
                        left++;
                    }
                }
            }
        }
        return ans;
    }
}