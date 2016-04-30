package com.zholdiyarov.transactions.helper.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by szholdiyarov on 4/29/16.
 * <p/>
 * THIS IS CUSTOM IMPLIMENTATION OF TREE. IT IS NOT ACTUAL A REALLY TREE DATA STRUCTURE BUT USES PRINCIPLE BEHIND IT.
 * BEST WAY TO CONVERT CURRENCIES WOULD BE GRAPHS.
 * <p/>
 * email: zholdiyarov@gmail.com
 */
public class Tree {
    private static HashMap<String, ArrayList<Edge>> trees = new HashMap<>();
    private static Tree tree;

    public static Tree getInstance() {
        return tree;
    }

    public static void addToTree(String label, Edge edge) {

        ArrayList<Edge> listOfEdges;
        if (!trees.containsKey(label)) {
            listOfEdges = new ArrayList<>();
            listOfEdges.add(edge);
            trees.put(label, listOfEdges);
        } else {
            listOfEdges = trees.get(label);
            listOfEdges.add(edge);
            trees.put(label, listOfEdges);
        }

    }


    public static double getRates(String currency) {

        double rate = 0.0; // this will be returned
        double directRate = getRatesFor(currency, "GBP"); // this checks if direct conversion is possible, if yes then returned.

        if (directRate != 0.0) { // direct conversion to GBP is possible
            return directRate;
        } else { // direct conversion to GBP is not possible

            ArrayList<Edge> edges = trees.get(currency); // get list of currency rate edges

            for (int i = 0; i < edges.size(); i++) {
                String nodeToCurrentCurrency = edges.get(i).getFromNode();
                double middleCurrencyRate = getRatesFor(nodeToCurrentCurrency, "GBP");

                if (middleCurrencyRate != 0) {
                    rate = getRatesFor(currency, nodeToCurrentCurrency);
                }
                rate *= getRatesFor(nodeToCurrentCurrency, "GBP");
            }
        }
        return rate;
    }

    private static double getRatesFor(String from, String to) {
        ArrayList<Edge> directEdgedCur = trees.get(to);
        for (int j = 0; j < directEdgedCur.size(); j++) {
            if (directEdgedCur.get(j).getFromNode().equalsIgnoreCase(from)) {
                return directEdgedCur.get(j).getRate();
            }
        }
        return 0.0;
    }

}