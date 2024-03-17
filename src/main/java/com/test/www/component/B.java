
package com.test.www.component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class B {

    public void ddd(){
        CompletableFuture<Void> oneExample = CompletableFuture.runAsync(()->{
            System.out.println("executeOne");
            try {
                TimeUnit.SECONDS.sleep(5);
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println("executeOne end...");;
        });

        oneExample.<String>thenApplyAsync((it)->{
            System.out.println("executeTwo");
            try{
                TimeUnit.SECONDS.sleep(5);
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println("executeTwo end...");
            return "execute tow result";
        });
    }
    public int maxProduct(int[] nums) {
        if(nums.length == 1) return nums[0];
        int[] maxList = new int[nums.length  + 1];
        int[] minList = new int[nums.length  + 1];
        maxList[0] = nums[0];
        minList[0] = nums[0];
        for(int i = 1; i < nums.length; i++){
            maxList[i] = Math.max(nums[i],
                    Math.max(maxList[i - 1] * nums[i], minList[i - 1] * nums[i] ));
            minList[i] = Math.min(nums[i],
                    Math.min(maxList[i - 1] * nums[i], minList[i - 1] * nums[i] ));
        }
        int ans = 0;
        for(int i = 0; i < maxList.length;i++){
            ans = Math.max(ans,maxList[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new B().maxProduct(new int[]{3,-1,4}));

        System.out.println(new B().maxProduct(new int[]{-2, 0}));

        System.out.println(new B().maxProduct(new int[]{-2, 0,-1}));

        System.out.println(new B().maxProduct(new int[]{2, 3, -2, 4}));
    }
}