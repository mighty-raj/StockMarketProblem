package com.bitspilani.mtech.dsa.stockmarketproblem;

public class LinearApproach {

    public static int startIdx = 0;
    public static int highIdx = 0;

    public static long cumSum = 0l;
    public static long highestTillNow = 0l;

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

    public static void main(String[] args) {

        //long startTime = System.currentTimeMillis();
        long startTime = System.nanoTime();

        int priceArr[] = { 13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7 };

        maxProfitInvestment(priceArr);

        System.out.println("Maximum Profit: " + highestTillNow);
        System.out.println("Day to buy: " + (startIdx + 1));
        System.out.println("Day to sell: " + (highIdx + 1));

        //long endTime = System.currentTimeMillis();
        //long duration = (endTime - startTime);  //Total execution time in milli seconds

        long endTime = System.nanoTime();
        long durationInNano = (endTime - startTime);

        System.out.println();
        System.out.println("Total Run Time in MilliSeconds: " + durationInNano);


    }

}
