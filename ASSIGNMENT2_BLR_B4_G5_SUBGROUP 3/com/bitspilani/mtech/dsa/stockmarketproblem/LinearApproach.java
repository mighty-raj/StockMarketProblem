package com.bitspilani.mtech.dsa.stockmarketproblem;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


/**
 * This Class is used to find
 * Day-to-Buy,
 * Day-to-Sell &
 * Total Profit gain
 * in a LINEAR APPROACH from given input source file
 *
 * @author ASSIGNMENT2_BLR_B4_G5_SUBGROUP 3
 * @version 1.0
 *
 *
 */
public class LinearApproach {

    public static int priceList[] = new int[17];
    public static int priceChangeList[] = new int[16];

    public static int startIdx = 0;
    public static int highIdx = 0;

    public static long cumSum = 0l;
    public static long highestTillNow = 0l;

    public static void main(String[] args) {

        if(args.length != 1){
            System.out.println("Please pass req. argument, input file path");
            System.exit(-1);
        }

        readInput(args[0]);

        maxProfitInvestment(priceChangeList);

        System.out.println("Maximum Profit: " + highestTillNow);
        System.out.println("Day to buy: " + (startIdx + 1));
        System.out.println("Day to sell: " + (highIdx + 1));



    }

    /**
     * This method is used to find the Day to Buy, Day to Sell & total profit gain in Linear approach
     * from given input source file
     *
     * @param stockPrice This is the array which holds, difference of stock price changes
     *
     * @return void This method doesn't return anything, instead prints our desired output to console
     */
    public static void maxProfitInvestment(int[] stockPrice) {

        for (int i = 0; i < stockPrice.length; i++){

            cumSum = stockPrice[i];

            for(int j=i+1; j < stockPrice.length; j++) {

                cumSum += stockPrice[j];

                if(cumSum > highestTillNow){
                    highestTillNow = cumSum;
                    highIdx = j;
                    startIdx = i;
                }
            }
        }

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

}
