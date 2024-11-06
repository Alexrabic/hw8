package org.example;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class HomeWork {

    /**
     * <h1>Задание 1.</h1>
     * Решить задачу
     * <a href="https://acm.timus.ru/problem.aspx?space=1&num=1316">https://acm.timus.ru/problem.aspx?space=1&num=1316</a>
     */
    public Double getProfit(List<String> actionList) {
        Treap<Double> treap = new Treap<>();

        double perTransaction = 0.01d;
        int transactions = 0;

        for (String action : actionList) {
            String[] split = action.split(" ");
            switch (split[0]) {
                case "BID":
                    treap.add(Double.parseDouble(split[1]), 1);
                    break;
                case "SALE":
                    Treap.Statistic stats = treap.getStats(Double.parseDouble(split[1]) - 0.01d, 100001.00d);
                    transactions += Math.min(stats == null ? 0 : stats.sumValue, Integer.parseInt(split[2]));
                    System.out.println(stats);
                    break;
                case "DEL" :
                    treap.remove(Double.parseDouble(split[1]));
                    break;
            }
        }

        return perTransaction * transactions;
    }

    /**
     * <h1>Задание 2.</h1>
     * Решить задачу <br/>
     * <a href="https://informatics.msk.ru/mod/statements/view.php?id=1974&chapterid=2782#1">https://informatics.msk.ru/mod/statements/view.php?id=1974&chapterid=2782#1</a><br/>
     */
    public List<Integer> getLeaveOrder(List<String> actionList) {
        Treap<Integer> treap = new Treap<>();
        Stack<Integer> result = new Stack<>();

        for (String actions : actionList) {
            String[] split = actions.split(" ");
            switch (split[0]) {
                case "+" :
                    Integer key = Integer.parseInt(split[1]);
                    key = result.isEmpty() ? key : result.peek() + key;
                    if (!treap.search(key)) {
                        treap.add(key, 1);
                    }
                    break;
                case "?" :
                    Integer minNode = treap.getMinNode(Integer.parseInt(split[1]) - 1, Integer.MAX_VALUE);
                    result.push(minNode == null ? -1 : minNode);
                    break;
            }
        }

        return new ArrayList<>(result);
    }

}
