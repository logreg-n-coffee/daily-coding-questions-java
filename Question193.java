/*
Given a array of numbers representing the stock prices of a company in chronological order, 
write a function that calculates the maximum profit you could have made from buying and selling that stock. 
You're also given a number fee that represents a transaction fee for each buy and sell transaction.

You must buy before you can sell the stock, but you can make as many transactions as you like.

For example, given [1, 3, 2, 8, 4, 10] and fee = 2, you should return 9, 
since you could buy the stock at 1 dollar, and sell at 8 dollars, 
and then buy it at 4 dollars and sell it at 10 dollars. 
Since we did two transactions, there is a 4 dollar fee, 
so we have 7 + 6 = 13 profit minus 4 dollars of fees.
 */

public class Question193 {
    // solve the problem with dynamic programming
    // time complexity of O(n) and a space complexity of O(n)
    public static void main(String[] args) {
        int[] prices = { 1, 3, 2, 8, 4, 10 };
        int fee = 2;
        int maxProfit = maxProfitWithTransactionFee(prices, fee);
        System.out.println("The maximum profit is: " + maxProfit);
    }

    public static int maxProfitWithTransactionFee(int[] prices, int fee) {
        int n = prices.length;

        // Create two integer arrays, buy and sell, both of size n
        int[] buy = new int[n];
        int[] sell = new int[n];

        // when you buy a stock, you are essentially "losing" money, and you need to
        // account for the fee as well.
        buy[0] = -prices[0] - fee;

        for (int i = 1; i < n; i++) {
            // For buy[i], it will be the maximum of either keeping the previous buy state
            // (buy[i-1]) or selling the stock from the previous state and buying a new one
            // at the current price minus the transaction fee (sell[i-1] - prices[i] - fee).
            buy[i] = Math.max(buy[i - 1], sell[i - 1] - prices[i] - fee);
            // For sell[i], it will be the maximum of either keeping the previous sell state
            // (sell[i-1]) or buying the stock from the previous state and selling it at the
            // current price (buy[i-1] + prices[i]).
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
        }

        // After the loop, return the last element of the sell array, which represents
        // the maximum profit we can achieve by the end of the given period.
        return sell[n - 1];
    }

}
