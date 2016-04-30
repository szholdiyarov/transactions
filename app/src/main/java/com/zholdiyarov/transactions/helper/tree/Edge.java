package com.zholdiyarov.transactions.helper.tree;

/**
 * Created by szholdiyarov on 4/29/16.
 * <p/>
 * THIS CLASS REPRESENTS AN EDGE IN THE IMPLEMENTED TREE.
 * <p/>
 * email: zholdiyarov@gmail.com
 */
public class Edge {
    private String fromNode;
    private String toNode;
    private double rate;

    public Edge(String fromNode, String toNode, double rate) {
        this.fromNode = fromNode;
        this.toNode = toNode;
        this.rate = rate;
    }

    public String getFromNode() {
        return fromNode;
    }

    public String getToNode() {
        return toNode;
    }

    public double getRate() {
        return rate;
    }


}
