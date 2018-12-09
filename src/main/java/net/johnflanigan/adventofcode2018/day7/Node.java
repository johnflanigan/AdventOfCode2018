package net.johnflanigan.adventofcode2018.day7;

import java.util.ArrayList;
import java.util.List;

public class Node implements Comparable<Node> {

    private static final int BASE_TIME_TO_PROCESS = 60;

    private String id;
    private int timeToComplete;
    private List<Node> children;
    private List<Node> parents;

    Node(String id) {
        this.id = id;
        this.timeToComplete = BASE_TIME_TO_PROCESS + id.charAt(0) - 64;
        this.children = new ArrayList<>();
        this.parents = new ArrayList<>();
    }

    public void addNextNode(Node node) {
        this.children.add(node);
    }

    public void addPrevNode(Node node) {
        this.parents.add(node);
    }

    public String getId() {
        return this.id;
    }

    public List<Node> getChildren() {
        return this.children;
    }

    public List<Node> getParents() {
        return this.parents;
    }

    @Override
    public int compareTo(Node o) {
        return this.id.compareTo(o.getId());
    }

    public int getTimeToComplete() {
        return this.timeToComplete;
    }

    public void setTimeToComplete(int timeToComplete) {
        this.timeToComplete = timeToComplete;
    }
}
