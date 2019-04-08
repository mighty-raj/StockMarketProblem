package com.bitspilani.mtech.dsa.stockmarketproblem;

import java.lang.*;

public class DivideNConquer {

    public static MaxProfit maxCrossingSubArray(int[] price, int low, int mid, int high) {

        int left_sum = Integer.MIN_VALUE;
        int lsum = 0;
        int maxLeftIdx =0;

        for(int i = mid; i>= low; i--){
            lsum += price[i];
            if (lsum > left_sum){
                left_sum = lsum;
                maxLeftIdx = i;
            }
        }


        int right_sum = Integer.MIN_VALUE;
        int rsum = 0;
        int maxRightIdx = 0;

        for(int i = mid+1; i<= high; i++){
            rsum += price[i];
            if (rsum > right_sum){
                right_sum = rsum;
                maxRightIdx = i;
            }
        }

        //System.out.println(maxLeftIdx + "\t" + maxRightIdx + "\t" + (left_sum + right_sum));

        return new MaxProfit(maxLeftIdx, maxRightIdx, left_sum + right_sum);

    }

    public static MaxProfit maxSubArray(int[] price, int low, int high) {

        MaxProfit maxProfitIndex = null;

        if (high == low) {

            return new MaxProfit(low, high, price[low]);

        } else {

            int mid = (int) Math.floor((high + low) / 2);

            MaxProfit leftProfit= maxSubArray(price, low, mid);
            MaxProfit rightProfit = maxSubArray(price, mid+1, high);
            MaxProfit crossingProfit = maxCrossingSubArray(price, low, mid, high);

            if ((leftProfit.totalGain >= rightProfit.totalGain) && (leftProfit.totalGain >= crossingProfit.totalGain)){
                maxProfitIndex = leftProfit;
            } else if ((rightProfit.totalGain >= leftProfit.totalGain) && (rightProfit.totalGain >= crossingProfit.totalGain)) {
                maxProfitIndex = rightProfit;
            } else {
                maxProfitIndex = crossingProfit;
            }

        }

        return maxProfitIndex;

    }

    public static void display(MaxProfit maxprofitidx){

        System.out.println("Maximum Profit: " + maxprofitidx.totalGain);
        System.out.println("Day to buy: " + (maxprofitidx.start + 1));
        System.out.println("Day to sell: " + (maxprofitidx.end + 1));

    }

    public static void main ( String[] args) {

        /*//long startTime = System.currentTimeMillis();
        long startTime = System.nanoTime();*/

        int priceArr[] = { 13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7 };

        MaxProfit highProfitIndex = maxSubArray(priceArr, 0, priceArr.length - 1);

        display(highProfitIndex);

        //long endTime = System.currentTimeMillis();
        //long duration = (endTime - startTime);  //Total execution time in milli seconds

        /*long endTime = System.nanoTime();
        long durationInNano = (endTime - startTime);

        System.out.println();
        System.out.println("Total Run Time in NanoSeconds: " + durationInNano);*/

    }
}
