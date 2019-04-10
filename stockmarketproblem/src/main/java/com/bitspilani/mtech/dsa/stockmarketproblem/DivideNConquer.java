package com.bitspilani.mtech.dsa.stockmarketproblem;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.*;
import java.util.ArrayList;

/**
 * This Class is used to find
 * Day-to-Buy,
 * Day-to-Sell &
 * Total Profit gain
 * in a DIVIDE AND CONQUER DESIGN APPROACH from given input source file
 *
 * @author ASSIGNMENT2_BLR_B4_G5_SUBGROUP 3
 * @version 1.0
 *
 *
 */
public class DivideNConquer {

    public static int priceList[] = new int[17];
    public static int priceChangeList[] = new int[16];


    public static void main ( String[] args) {

        if(args.length != 1){
            System.out.println("Please pass req. argument, input file path");
            System.exit(-1);
        }

        //System.out.println(args[0]);

        readInput(args[0]);

        MaxProfit highProfitIndex = maxSubArray(priceChangeList, 0, priceChangeList.length - 1);

        display(highProfitIndex);

    }

    /**
     * Displays starting index, high index, total max profit gain with range b/w start and high index to CONSOLE
     *
     * @param maxprofitidx - MaxProfit object, which holds Start Index, High Index and Total profit gain
     *                     between these indices.
     */
    public static void display(MaxProfit maxprofitidx){

        System.out.println("Maximum Profit: " + maxprofitidx.totalGain);
        System.out.println("Day to buy: " + (maxprofitidx.start + 1));
        System.out.println("Day to sell: " + (maxprofitidx.end + 1));

    }

    /**
     * This method is used to read the input file, containing stock pirce change list for 17days.
     * @param inpFile This is the input file to read stock price list
     * @return void This method doesn't return anything, instead read's input file
     */
    public static void readInput(String inpFile){

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(inpFile))) {
            String line = bufferedReader.readLine();

            int i = 0;

            while ((line != null) && (i < priceList.length)) {

                String[] lineSplit = line.split("\\/");

                priceList[i] = Integer.parseInt(lineSplit[1]);
                i++;

                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found: " + inpFile);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(int x=1; x < priceList.length; x++){
            int chng = priceList[x] - priceList[x-1];
            priceChangeList[x-1] = chng;
        }

    }

    /**
     * This is the main method, which applies recursion to solve max sub-array problem with DIVIDE AND CONQUER DESIGN STRATEGY
     *
     * @param price - price change array
     * @param low - starting index of the array
     * @param high - ending index of the array
     * @return  returns a MaxProfit object, which holds, start idx, high idx and total profit gain between start and end index
     */
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


    /**
     * This method finds the starting idx and high idx and profit gain for the crossing sub array,
     * which is crossing between left and right sub-array's from mid-point
     *
     * @param price - Price change list array
     * @param low - starting index of array
     * @param mid - middle index of the given price change array
     * @param high - end index of given price change array
     * @return
     */
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

        return new MaxProfit(maxLeftIdx, maxRightIdx, left_sum + right_sum);

    }
}
