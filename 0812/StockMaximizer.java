import java.util.*;

public class StockMaximizer {

    static class Transaction {
        int buyPrice;
        int sellPrice;
        int profit;
        Transaction(int buy, int sell) {
            this.buyPrice = buy;
            this.sellPrice = sell;
            this.profit = sell - buy;
        }
    }

    public static String maxProfitWithDetails(int[] prices, int k) {
        if (prices == null || prices.length < 2 || k <= 0) return "0";

        int n = prices.length;
        PriorityQueue<Transaction> maxHeap =
                new PriorityQueue<>((a, b) -> b.profit - a.profit);

        int buy = 0, sell = 0;
        while (buy < n) {
            while (buy < n - 1 && prices[buy] >= prices[buy + 1]) buy++; // 跳過價格持平或下降的日子，直到找到一個比隔天低的價位
            sell = buy + 1;

            while (sell < n && prices[sell] >= prices[sell - 1]) sell++; // 走到價格下降的前一天，sell - 1 就是賣出日

            // 計算利潤並放入 Max Heap
            if (buy < n && sell - 1 > buy) {
                int profit = prices[sell - 1] - prices[buy];
                if (profit > 0) {
                    maxHeap.offer(new Transaction(prices[buy], prices[sell - 1]));
                }
            }
            buy = sell;
        }
        
        
        // 取前 K 筆最大交易
        int totalProfit = 0;
        List<Transaction> chosen = new ArrayList<>();
        while (k > 0 && !maxHeap.isEmpty()) {
            Transaction t = maxHeap.poll();
            totalProfit += t.profit;
            chosen.add(t);
            k--;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(totalProfit).append(" (");
        for (int i = 0; i < chosen.size(); i++) {
            Transaction t = chosen.get(i);
            sb.append("在價格").append(t.buyPrice).append("時買入，在價格")
              .append(t.sellPrice).append("時賣出");
            if (i < chosen.size() - 1) sb.append("；");
        }
        sb.append(")");
        return sb.toString();
    }

    public static void main(String[] args) {
        int[][] testPrices = {
            {2, 4, 1},
            {3, 2, 6, 5, 0, 3},
            {1, 2, 3, 4, 5}
        };
        int[] ks = {2, 2, 2};

        for (int i = 0; i < testPrices.length; i++) {
            System.out.print("價格：[");
            for (int j = 0; j < testPrices[i].length; j++) {
                System.out.print(testPrices[i][j]);
                if (j < testPrices[i].length - 1) System.out.print(",");
            }
            System.out.println("], K=" + ks[i]);

            String ans = maxProfitWithDetails(testPrices[i], ks[i]);
            System.out.println("答案：" + ans);
            System.out.println();
        }
    }
}
