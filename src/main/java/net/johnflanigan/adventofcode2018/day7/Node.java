package net.johnflanigan.adventofcode2018.day7;

import java.util.ArrayList;
import java.util.List;

public class Node implements Comparable<Node> {

    private String id;
    private List<Node> next;
    private List<Node> prev;

    Node(String id) {
        this.id = id;
        this.next = new ArrayList<>();
        this.prev = new ArrayList<>();
    }

    public void addNextNode(Node node) {
        this.next.add(node);
    }

    public void addPrevNode(Node node) {
        this.prev.add(node);
    }

    public String getId() {
        return this.id;
    }

    public List<Node> getNext() {
        return this.next;
    }

    public List<Node> getPrev() {
        return this.prev;
    }

    @Override
    public int compareTo(Node o) {
        return this.id.compareTo(o.getId());
    }
}
